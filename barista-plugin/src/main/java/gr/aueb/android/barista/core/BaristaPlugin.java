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
//todo use gradle properties to inject variables to  BuildConfig.
import com.android.build.gradle.AndroidConfig;
import com.android.build.gradle.BaseExtension;
import com.android.build.gradle.LibraryExtension;
import com.android.build.gradle.api.AndroidArtifactVariant;
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension;
import com.android.builder.model.ClassField;
import com.google.wireless.android.sdk.stats.AndroidView;
import gr.aueb.android.barista.server.HttpServerManager;
import groovy.lang.Closure;
import org.glassfish.grizzly.utils.ArraySet;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.logging.LogLevel;
import org.gradle.api.tasks.TaskState;
import com.android.build.gradle.api.BaseVariant;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
       // project.setProperty("BARISTA_PORT", new Integer(2));
        // PLUGIN LOGIG
        // todo check for best practises and code style
        // check if target project is an android project
        if(isAndroidProject()){
            System.out.println("This is an Android Project");
            project.afterEvaluate(new Action<Project>() {

                @Override
                public void execute(Project project) {
                   // project.setProperty("BARISTA_PORT", new Integer(2));

                    Task targetTask = project.getTasks().findByPath(ASSEMBLE_DEBUG_ANDROID_TEST);
                    if(targetTask == null ){
                        project.getLogger().log(LogLevel.ERROR,"No Android task '"+ASSEMBLE_DEBUG_ANDROID_TEST+"' found");

                    }
                    else{
                        project.getLogger().log(LogLevel.ERROR,"Task  '"+ASSEMBLE_DEBUG_ANDROID_TEST+"' Found !!! ");
                        // todo debug only
                        scanProject();
                        hookPortConfiguration();
                        deployDispatcherServer(targetTask);
                        hookServerStopTask();
                    }
                }
            });

        }
        // The target project is NOT an android project
        else{
            System.out.println("This is not an Android Project");

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
       //return isAndroidApp || isAndroidTest || isAndroidFeature || isAndroidInstantApp;
    }

    private void deployDispatcherServer(Task targetTask){

        targetTask.doLast(new Action<Task>() {
            @Override
            public void execute(Task task) {

                BaristaConfigurationExtension settings = project.getExtensions().findByType(BaristaConfigurationExtension.class);
                if(settings != null){
                    HttpServerManager.setPort(settings.getPort());
                }
                System.out.println("[BARISTA]: Starting Server on "+ HttpServerManager.getBaseUri());
                HttpServerManager.startServer();
            }
        });
    }

    // Jersey server should be automaticaly shut down when connectedAndroidTest task is completed
    // todo There are two ways to run an  instrumented test. Must find how to close when using 2
    //  1.  from command lind with 'gradle connectedAndroidTest'
    //  2.  with adb using '$ adb shell am instrument -w <test_package_name>/<runner_class>'
    // Curently the plugin shuts down the server if the first way is used.

    private void hookServerStopTask(){
        System.out.println("[BARISTA] : Hook Server Stop Task");

        Task targetTask = project.getTasks().findByPath(CONNECTED_ANDROID_TEST);
        targetTask.doLast(new Action<Task>() {

            @Override
            public void execute(Task task) {
                System.out.println("[BARISTA]: Closing Server");
                HttpServerManager.stopServer();
            }
        });

//        targetTask.configure(new Closure() {
//        })
    }

    //todo commented out due to serilization bug

    private void hookPortConfiguration(){

        BaseAppModuleExtension androidExtension= getAndroidExtension();
        if(androidExtension != null) {
            androidExtension.getDefaultConfig()
                    .buildConfigField("Integer","BARISTA_PORT",""+getProvidedPort()+"");

        }

    }

    /**
     * Function that returns the android extension of the android project.
     *
     * @return
     */

    private BaseAppModuleExtension getAndroidExtension(){
        Object o = project.getExtensions().findByName(this.ANDROID_EXTENSION_NAME);

        System.out.println("[BARISTA-PLUGIN]: Android Extension Class Name: "+o.getClass().getName());
        if(o instanceof com.android.build.gradle.internal.dsl.BaseAppModuleExtension){
            System.out.println("[BARISTA-PLUGIN]: INSTACE OF BaseAppModuleExtension");
            return (BaseAppModuleExtension) project.getExtensions().findByName(this.ANDROID_EXTENSION_NAME);
        }

       return null;

    }

    /**
     *
     * @return
     */
    private Integer getProvidedPort(){
        BaristaConfigurationExtension settings = project.getExtensions().findByType(BaristaConfigurationExtension.class);
        System.out.println("[BARISTA]: Found Port >> "+settings.getPort() );
        return settings.getPort();
    }


    private void scanProject(){
        int projectNum = project.getAllprojects().size();
        System.out.println("[BARISTA-PLUGIN] Number of Projects: "+projectNum);


    }

}
