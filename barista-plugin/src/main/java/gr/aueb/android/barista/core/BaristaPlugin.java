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
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.logging.LogLevel;

public class BaristaPlugin implements Plugin<Project> {
    // todo check for assemble release test
    private String TARGET_TASK = "assembleDebugAndroidTest";

    public void apply(Project project){

        // check if target project is an android project
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
                        deployDispatcherServer(targetTask,project);

                    }
                }
            });

        }
        // The target project is NOT an android project
        else{
            System.out.println("This is not an Android Project");
            /**
             * TODO Delete all if noe an android Project. Only for testing purposes
             * TESTING SECTION
             * deploys either way a deployServer task just to be able to run on none android projects
             */
            project.getLogger().log(LogLevel.ERROR,"EXECUTING SOMETHING NOW");
            project.getTasks().create("deployTestDispacherServerTask", TestDispacherServerTask.class,
                    new Action<TestDispacherServerTask>() {
                        @Override
                        public void execute(TestDispacherServerTask testDispacherServerTask) {
                            testDispacherServerTask.deployServer();
                        }
                    });

        }


    }

    /**
     * Function that determins if target project is an android project based on the imported plugins
     * // todo find source
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

    private void deployDispatcherServer(Task targetTask, Project project){

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
