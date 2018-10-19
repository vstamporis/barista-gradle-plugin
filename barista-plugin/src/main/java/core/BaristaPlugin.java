/**
 * Author: Tsiskomichelis Stelios
 * Created On: 17/10/2018
 * Project: android-plugin
 * <p>
 * ClassName: TestDispacherServerTask
 * Role: Implements the task that deploys the adb test dispacher
 * Description:
 */
package core;

import groovy.lang.Closure;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.logging.LogLevel;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.tasks.TaskAction;
import sun.rmi.runtime.Log;

public class BaristaPlugin implements Plugin<Project> {
    private String TARGET_TASK = "assembleDebugAndroidTest";

    public void apply(Project project){

/**
 * Ad-hoc execution method style
 */
//          project.getTasks().create("deployTestDispacherServerTask", TestDispacherServerTask.class,
//                    new Action<TestDispacherServerTask>() {
//                @Override
//                public void execute(TestDispacherServerTask testDispacherServerTask) {
//                    testDispacherServerTask.deployServer();
//                }
//            });



      //  project.getTasks().create("deployTestDispacherServerTask", TestDispacherServerTask.class);


                //.doLast((Action<TestDispacherServerTask>) testDispacherServerTask -> testDispacherServerTask.deployServer());
        //make sure that android plugin is present ( id 'com.android.application')
//        project.getTasks().findByName("deployTestDispacherServerTask").dependsOn("generateDebugSources").doLast(new Action<Task>() {
//            @Override
//            public void execute(Task task) {
//                project.getLogger().log(LogLevel.LIFECYCLE,"Running After assembleDebugAndroid");
//            }
//        });

        // Add task to project


        if(isAndroidProject(project)){
            System.out.println("This is an Android Project");
            project.afterEvaluate(new Action<Project>() {
                @Override
                public void execute(Project project) {
                    System.out.println("After Evaluation!");
                    Task targetTask = project.getTasks().findByPath(TARGET_TASK);
                    if(targetTask == null ){
                        project.getLogger().log(LogLevel.ERROR,"No Android task '"+TARGET_TASK+"' found");

                    }
                    else{
                        project.getLogger().log(LogLevel.ERROR,"Task  '"+TARGET_TASK+"' Found !!! ");
                        targetTask.doLast(new Action<Task>() {
                            @Override
                            public void execute(Task task) {
                                project.getLogger().log(LogLevel.ERROR,"EXECUTING SOMETHING NOW");
                                project.getTasks().create("deployTestDispacherServerTask", TestDispacherServerTask.class,
                                        new Action<TestDispacherServerTask>() {
                                            @Override
                                            public void execute(TestDispacherServerTask testDispacherServerTask) {
                                                testDispacherServerTask.deployServer();
                                            }
                                        });
                            }
                        });

                    }
                }
            });




//            project.getTasks().create("deployTestDispacherServerTask", TestDispacherServerTask.class,
//                    new Action<TestDispacherServerTask>() {
//                @Override
//                public void execute(TestDispacherServerTask testDispacherServerTask) {
//                    testDispacherServerTask.deployServer();
//                }
//            });
                 //   .dependsOn("app:assembleDebugAndroidTest");

        }else{
            System.out.println("This is not an Android Project");
        }


    }

    /**
     * Function that determins if target project is an anroid project based on the imported plugins
     *
     * @param project
     * @return true if is android project, false if not
     */
    private static boolean isAndroidProject(final Project project) {

        final boolean isAndroidLibrary = project.getPlugins().hasPlugin("com.android.library");
        final boolean isAndroidApp = project.getPlugins().hasPlugin("com.android.application");
        final boolean isAndroidTest = project.getPlugins().hasPlugin("com.android.test");
        final boolean isAndroidFeature = project.getPlugins().hasPlugin("com.android.feature");
        final boolean isAndroidInstantApp = project.getPlugins().hasPlugin("com.android.instantapp");

        return isAndroidLibrary || isAndroidApp || isAndroidTest || isAndroidFeature || isAndroidInstantApp;
    }

}
