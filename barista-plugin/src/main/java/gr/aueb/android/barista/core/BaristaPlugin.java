/**
 * Author: Tsiskomichelis Stelios
 * Created On: 17/10/2018
 * Project: barista-plugin
 * <p>
 * ClassName: TestDispacherServerTask
 * Role: Implements the task that deploys the adb test dispacher
 * Description:
 */
package gr.aueb.android.barista.core;

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension;
import gr.aueb.android.barista.server.HttpServerManager;
import gr.aueb.android.barista.utilities.BaristaLoger;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.logging.LogLevel;

public class BaristaPlugin implements Plugin<Project> {

    //The android task that assembles the debug test app
    private final String ASSEMBLE_DEBUG_ANDROID_TEST = "assembleDebugAndroidTest";

    // the android task that runs the android instrumented tests of the project
    //todo try this task for all cases 1 & 2
    private String CONNECTED_ANDROID_TEST = "connectedDebugAndroidTest";
    
    private final String ANDROID_EXTENSION_NAME = "android";

    private Project project;

    public void apply(Project project){
        this.project = project;

        // load the configuration extension
        project.getExtensions().create("baristaSettings", BaristaConfigurationExtension.class);

        project.getTasks().create("stopBaristaServer", new Action<Task>() {
            @Override
            public void execute(Task task) {

                project.getLogger().log(LogLevel.ERROR,"Build Failed. Closing Barista server");
                try {
                    HttpServerManager.stopServer();
                }catch (NullPointerException e){
                    project.getLogger().log(LogLevel.ERROR,"Null Pointer Exeption");
                }
            }
        });
        // check if target project is an android project
        if(isAndroidProject()){

            System.out.println("[BARISTA-PLUGIN] This is an Android Project");



            project.afterEvaluate(new Action<Project>() {

                @Override
                public void execute(Project project) {
                    // check if task assembleDebugAndroidTest is about to be executed
                    Task targetTask = project.getTasks().findByPath(ASSEMBLE_DEBUG_ANDROID_TEST);

                    targetTask.finalizedBy("stopBaristaServer");

                    // if no tests are runnig do nothing
                    if(targetTask == null ){
                        project.getLogger().log(LogLevel.ERROR,"No Android task '"+ASSEMBLE_DEBUG_ANDROID_TEST+"' found");
                    }

                    else{
                        project.getLogger().log(LogLevel.ERROR,"Task  '"+ASSEMBLE_DEBUG_ANDROID_TEST+"' Found !!! ");

                        // inject port configuration for barista http client
                        hookPortConfiguration();

                        // deploy the server when ready to run connected tests
                        deployDispatcherServer(targetTask);

                        // stop server when connected android tests finishes
                        hookServerStopTask();

                    }
                }
            });

        }

        else{    // If the target project is NOT an android project, do nothing
            BaristaLoger.print("This is not an Android Project");
        }


    }

    /**
     * Function that determins if target project is an android project based on the imported plugins
     * // todo find source
     *
     * @return true if is android project, false if not
     */
    private boolean isAndroidProject() {

        final boolean isAndroidLibrary = this.project.getPlugins().hasPlugin("com.android.library");
        final boolean isAndroidApp = this.project.getPlugins().hasPlugin("com.android.application");
        final boolean isAndroidTest = this.project.getPlugins().hasPlugin("com.android.test");
        final boolean isAndroidFeature = this.project.getPlugins().hasPlugin("com.android.feature");
        final boolean isAndroidInstantApp = this.project.getPlugins().hasPlugin("com.android.instantapp");

        return isAndroidLibrary || isAndroidApp || isAndroidTest || isAndroidFeature || isAndroidInstantApp;

    }

    private void deployDispatcherServer(Task targetTask){

        targetTask.doLast(new Action<Task>() {
            @Override
            public void execute(Task task) {

                // load the provided extension settings
                BaristaConfigurationExtension settings = project.getExtensions().findByType(BaristaConfigurationExtension.class);


                if(settings != null){   // if settings are provided

                    // configure server to run on the provided listening port
                    HttpServerManager.setPort(settings.getPort());
                }

                //start the server on localhost
                BaristaLoger.print("Starting Server on "+ HttpServerManager.getBaseUri());
                HttpServerManager.startServer();
            }
        });
    }

    // Jersey server should be automaticaly shut down when connectedAndroidTest task is completed
    // todo There are two ways to run an  instrumented test. Must find how to close when using 2
    //  1.  from command lind with 'gradle connectedAndroidTest'
    //  2.  with adb using '$ adb shell am instrument -w <test_package_name>/<runner_class>'
    // Curently the plugin shuts down the server if the first way is used.
    /**
     *
     */
    private void hookServerStopTask(){

        BaristaLoger.print("Hooking task for stopping server");

        Task targetTask = project.getTasks().findByPath(CONNECTED_ANDROID_TEST);

        targetTask.doLast("shutDownServer",new Action<Task>() {

            @Override
            public void execute(Task task) {
                BaristaLoger.print("Closing Server");
                HttpServerManager.stopServer();
            }
        });


    }

    /**
     *
     */
    private void hookPortConfiguration(){
        BaseAppModuleExtension androidExtension= getAndroidExtension();
        if(androidExtension != null) {
            androidExtension.getDefaultConfig().buildConfigField("Integer","BARISTA_PORT",""+getProvidedPort()+"");
        }
    }

    /**
     * Function that returns the android extension of the android project.
     * This will expose the confidurations given by the developer inside the android gradle.build block
     * @return
     */
    private BaseAppModuleExtension getAndroidExtension(){
        Object o = project.getExtensions().findByName(this.ANDROID_EXTENSION_NAME);

        BaristaLoger.print("Loading android module extension: "+o.getClass().getName());
        if(o instanceof com.android.build.gradle.internal.dsl.BaseAppModuleExtension){
            return (BaseAppModuleExtension) project.getExtensions().findByName(this.ANDROID_EXTENSION_NAME);
        }

        return null;

    }

    /**
     * Scans for the baristaSettings extension and extracts the provided port number
     *
     * @return  the port number requested by the user
     */
    private Integer getProvidedPort(){
        BaristaConfigurationExtension settings = project.getExtensions().findByType(BaristaConfigurationExtension.class);
        int port = settings.getPort();
        BaristaLoger.print("Loaded given configuration for port "+port);
        return port;
    }

    /**
     * todo DEBUG-ONLY
     */
    private void scanProject(){
        int projectNum = project.getAllprojects().size();
        BaristaLoger.print("Number of Projects: "+projectNum);

    }

}
