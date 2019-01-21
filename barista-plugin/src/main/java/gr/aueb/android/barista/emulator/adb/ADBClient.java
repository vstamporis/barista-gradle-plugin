/**
 * Author: Tsiskomichelis Stelios
 * Created On: 2/12/2018
 * Project: barista-plugin
 * <p>
 * ClassName: ProcessExecutingFactory
 * Role:
 * Description:
 */
package gr.aueb.android.barista.emulator.adb;

/**
 *  using articles 1.  https://www.javaworld.com/article/2071275/core-java/when-runtime-exec---won-t.html
 *                 2. https://www.baeldung.com/run-shell-command-in-java
 */



import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;



public class ADBClient {

    private final static Logger LOGGER = Logger.getLogger(ADBClient.class.getName());
    private final static int UID =  new Random().nextInt(1000);
    private static ADBClient INSTANCE = null;
    private String shell;
    Process process;

    ArrayList<String> connectedDeviceIDs; // list to strore connected devices names for adb -s use

    Hashtable<String,String> emulatorTokens;

    //todo Remove running test counting functionality from this object.
    private static int activeTargetsTesting = 0;

    private ADBClient(){
        // determineOs();  // determine the OS of the host machine
        connectedDeviceIDs = new ArrayList<>();
        connectedDeviceIDs = listDevices();
        emulatorTokens = new  Hashtable<String,String>();
        activeTargetsTesting = connectedDeviceIDs.size();
        System.out.println( "[BARISTA-PLUGIN] Total devices: "+activeTargetsTesting);

        //for each device generate and publish a key.
        // Publish means pushing it to the device storage to be accesed by the client
        for (String device: connectedDeviceIDs) {
            generateDeviceToken(device);
        }
    }

    public static ADBClient getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ADBClient();
        }
        return INSTANCE;
    }

    /**
     * Returns the emulator-id based on the token provided
     * @return The emulator-id or null
     */
    public String verifyToken(String token){
        String emulatorID = this.emulatorTokens.get(token);
        return emulatorID;
    }

    /**
     * Generates a file containing the device token of the emulatorID
     * @param emulatorID
     * @return The created file
     */
    private void generateDeviceToken(String emulatorID){

        if(this.connectedDeviceIDs.contains(emulatorID)) {
            // generate a unique ID
            UUID deviceToken = UUID.randomUUID();
            //map the token to the device
            emulatorTokens.put(deviceToken.toString(), emulatorID);
            BaristaLoger.print("Giving device " + emulatorID + ", token: " + deviceToken);

            //generate file. The file will be stored at 'C:\Users\s.tsisko\Documents\barista-gradle-plugin\barista-plugin'
            String tokenFileName = "barista-token.txt";
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(tokenFileName);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(deviceToken.toString());
                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            //push the file to the emulator
            this.pushFile(emulatorID,tokenFileName,this.DEFAULT_EMULATOR_STORAGE_PATH);

            //delete the file from local storage
            File f = new File(tokenFileName);
            if(!f.delete()){
                BaristaLoger.print("Could not delete properly temporary token file. My cause conflict later");
            }


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
                    result.add(deviceId);
                }
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean hasActiveTestsRunning(){
        System.out.println("[BARISTA-PLUGIN-"+UID+"]: Checking Active tests: "+activeTargetsTesting);
        return (activeTargetsTesting > 0);
    }

    public void testOnEmulatorFinished(){
        System.out.println("[BARISTA-PLUGIN-"+UID+"] Decrease running tests By 1");
        activeTargetsTesting --;
    }

    public int getCountRunningTests(){
        return activeTargetsTesting;
    }

    public boolean changeDimension(String emulatorID, int width, int height){

        try {
            System.out.println("[BARISTA-PLUGIN] Executing resize to "+emulatorID);
            Process p = Runtime.getRuntime().exec("adb -s "+emulatorID+" shell wm size " + height + "x" + width);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean resetDimension(String deviceID){

        try {
            BaristaLoger.print("Reseting screen size for device "+deviceID);
            Process p = Runtime.getRuntime().exec("adb -s "+deviceID+" shell wm size reset");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Pushes a specified file in the specifies location of the specified running emulator.
     * This is a blocking function because it waits for the file to be uploaded
     * @param emulatorID
     * @param filePath
     * @param destination
     * @return
     */
    public boolean pushFile(String emulatorID, String filePath, String destination){
        BaristaLoger.print("Pushing access token to device:"+ emulatorID);
        BaristaLoger.print("Executing: "+"adb -s "+emulatorID+" push "+filePath+" "+destination);
        ProcessBuilder pb = new ProcessBuilder("adb", "-s", emulatorID,"push",filePath,destination);
       // pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        //todo maybe handle the error output and return false values in case of error
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        Process p = null;
        try {
            p = pb.start();
            // must wait for the upload to finish before returning
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line ="";
            while( (line = output.readLine()) !=null){
               ;;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Hashtable<String,String> getTokenMap(){
        return this.emulatorTokens;
    }
}
