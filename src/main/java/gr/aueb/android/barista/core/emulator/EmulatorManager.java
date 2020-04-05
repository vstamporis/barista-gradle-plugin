/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: EmulatorManager
 * Role: Determins the connected devices on the host machine, assigns and manages session tokens for each device
 * Description: Singleton
 *              Checks witch emulators are connected to the host machine then generates unique identifiers (sessionTokens)
 *              that pushes them in the form of file to each emulator. The mapping of ecmulator to session token is
 *              managed by this object. This object also provides methods to easily execute utility functions directly to an emulator.
 */
package gr.aueb.android.barista.core.emulator;

import gr.aueb.android.barista.utilities.BaristaLogger;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class EmulatorManager {
    /**
     *  Single instance to be used by other components
     */
    private static EmulatorManager INSTANCE = null;

    /**
     *  The default storage path inside the emulator to push various files
     */
    private final String DEFAULT_EMULATOR_STORAGE_PATH = "/storage/emulated/0";

    /**
     *  A list to store the deviceIds of each connecteed emulator
     */
    ArrayList<String> connectedDevices;

    /**
     *  A Map collection to store deviceId,sessionToken pairs
     */
    Hashtable<String,String> emulatorTokens;

    /**
     *  A string contatingn the package name of the testing application
     */
    private static String packageName;

    /**
     *  Private costructor that identifies the connected emulators and assigns to them their sessionTokens
     *  After the execution of the constructor the connectedDevices lsit contains the ids of the connected emulators,
     *  the emulatorTokens map contains the deviceId - sessionToken pairs and all the emulators contain in their local storage
     *  the sessionTokens.
     *
     */
    private EmulatorManager(){

        BaristaLogger.print("Initializing ADB client");

        //todo check if null and throw exception
        connectedDevices = getConnectedDevices();

        emulatorTokens = new  Hashtable<>();

        //for each device generate and publish a key.
        // Publish means pushing it to the device storage to be accesed by the client
        for (String device: connectedDevices) {
            generateDeviceToken(device);
        }
    }

    public static EmulatorManager getManager(){
        if(INSTANCE == null){
            INSTANCE = new EmulatorManager();
        }
        return INSTANCE;
    }

    /**
     * Instatiates again the emulator manager in order to refresh and re - assign tokens to the devices
     *
     */
    public static void revalidate() {
        INSTANCE = new EmulatorManager();
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
     * Runs the adb devices command to list all devices. Then forms an ArrayList containing the devices ids retrieved
     * @return The list containing all the ids of the connected devices in String format
     */
    public ArrayList<String> getConnectedDevices(){
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

    /**
     * Generates a file containing the device token of the emulatorID
     * @param emulatorID
     *
     */
    private void generateDeviceToken(String emulatorID){

        if(this.connectedDevices.contains(emulatorID)) {
            // generate a unique ID
            UUID deviceToken = UUID.randomUUID();
            //map the token to the device
            emulatorTokens.put(deviceToken.toString(), emulatorID);
            BaristaLogger.print("Giving device " + emulatorID + ", token: " + deviceToken);

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
            //todo na pernei to path dynamika apo to plugin
            this.pushFile(emulatorID,"C:\\Users\\s.tsisko\\Downloads\\AthensTour.kml",this.DEFAULT_EMULATOR_STORAGE_PATH);

            //delete the file from local storage
            File f = new File(tokenFileName);
            if(!f.delete()){
                BaristaLogger.print("Could not delete properly temporary token file. My cause conflict later");
            }


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
        BaristaLogger.print("Pushing access token to device:"+ emulatorID);
        BaristaLogger.print("Executing: "+"adb -s "+emulatorID+" push "+filePath+" "+destination);
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

    /**
     * Returns the <token,emulator> map containing the mapings of given tokens to connected emulators.
     * @return  The map
     */
    public Hashtable<String,String> getTokenMap(){
        return this.emulatorTokens;
    }

    public static void setPackageName(String applicationID) {
        packageName = applicationID ;
    }

    public static String getPackageName() {
        return  packageName;
    }

    public boolean setPermissions(String permissions) {

        try {
            for(String device: getConnectedDevices()){
                // build command
                ProcessBuilder pb = new ProcessBuilder("adb","-s",device, "shell", "pm", "grant", packageName, permissions);
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);
                //pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                BaristaLogger.print("adb -s "+device+" shell pm grant "+packageName+" "+permissions);
                //execute command
                Process p = pb.start();
                //read the output
                BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
                output.lines().forEach( s -> System.out.println(s));
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            BaristaLogger.print("NO EMULATOR FOUND");
            return false;
        }

    }
}
