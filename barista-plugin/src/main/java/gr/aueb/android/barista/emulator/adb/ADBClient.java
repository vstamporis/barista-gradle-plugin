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



import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;



public class ADBClient {

    private final static Logger LOGGER = Logger.getLogger(ADBClient.class.getName());
    private final static int UID =  new Random().nextInt(1000);

    private String shell;
    Process process;
    ArrayList<String> connectedDeviceIDs; // list to strore connected devices names for adb -s use

    //todo Remove running test counting functionality from this object.
    private static int activeTargetsTesting = 0;

    public ADBClient(){
        determineOs();  // determine the OS of the host machine
        activeTargetsTesting = listDevices().size();
        LOGGER.info("Total Tests: "+activeTargetsTesting);
    }

    /*
        todo NOT CURENTLY USED
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

    public  ArrayList<String> listDevices(){
        ArrayList<String> result = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec("adb devices"); // list connected devices

            BufferedReader stdOut =  new BufferedReader(new InputStreamReader(p.getInputStream())); // get the output stream

            String line = null;
            stdOut.readLine(); // eat the first row

            while((line = stdOut.readLine()) != null){          //parse lines of output stream
                if(!line.isEmpty()) {                                      // expected output is 'emulator-xxxx device'.
                    String deviceId = line.split("\t")[0];    // get only 'emulator-xxxx' part
//                    System.out.println(deviceId);
//                    System.out.println("[BARISTA-PLUGIN]: ADDING DEVICE: '"+deviceId+"' ");
                    result.add(deviceId);
                }// store the device id
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean hasActiveTestsRunning(){
        System.out.println("[BARISTA-PLUGIN-"+UID+"]: Checking Active tests: "+activeTargetsTesting);
        return (activeTargetsTesting > 0);
    }

    public static void testOnEmulatorFinished(){
        System.out.println("[BARISTA-PLUGIN-"+UID+"] Decrease running tests By 1");
        activeTargetsTesting --;
    }

    public static int getCountRunningTests(){
        return activeTargetsTesting;
    }

    public boolean changeDimension(int width, int height){
        try {
            ArrayList<String> deviceSelector = this.listDevices(); //
            if(deviceSelector != null && deviceSelector.size() > 1) {
                for(String device : deviceSelector){
                    Process p = Runtime.getRuntime().exec("adb -s "+device+" shell wm size " + height + "x" + width);
                }
            }
            else{
                Process p = Runtime.getRuntime().exec("adb shell wm size " + height + "x" + width);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean resetDimension(){
        try {
            ArrayList<String> deviceSelector = this.listDevices(); //
            if(deviceSelector != null && deviceSelector.size() > 1) {
                for(String device : deviceSelector){
                    Process p = Runtime.getRuntime().exec("adb -s "+device+" shell wm size reset");
                }
            }
            else{
                Process p = Runtime.getRuntime().exec("adb shell wm size reset");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
