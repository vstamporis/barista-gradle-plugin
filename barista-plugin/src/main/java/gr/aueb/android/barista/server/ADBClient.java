/**
 * Author: Tsiskomichelis Stelios
 * Created On: 2/12/2018
 * Project: barista-plugin
 * <p>
 * ClassName: ProcessExecutingFactory
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server;

/**
 *  using articles 1.  https://www.javaworld.com/article/2071275/core-java/when-runtime-exec---won-t.html
 *                 2. https://www.baeldung.com/run-shell-command-in-java
 */

import java.io.IOException;
import java.util.logging.Logger;

public class ADBClient {

    private final static Logger LOGGER = Logger.getLogger(ADBClient.class.getName());
    private String shell;
    Process process;

    public ADBClient(){

        determineOs();

    }

    /*
        Determines the operating system of the host machine and sets the appropriate shell program
        Windows => cmd.exe
        Linux Based => shell
     */
    private  void determineOs(){
        // find the operating system of the host machine
        String hostOsName = System.getProperty("os.name").toLowerCase();
        if(hostOsName.startsWith("windows")){
            LOGGER.info("Detected Windows operating system. Setting default shell to 'cmd.exe'");
            this.shell = "cmd.exe";
        }
        else{
            LOGGER.info("Detected Linux based operating system. Setting default shell to 'sh'");
            this.shell = "sh";
        }

    }

    public boolean changeDimension(int width, int height){
        try {
            Process p = Runtime.getRuntime().exec("adb shell wm size "+height+"x"+width);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean resetDimension(){
        try {
            Process p = Runtime.getRuntime().exec("adb shell wm sizereset");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
