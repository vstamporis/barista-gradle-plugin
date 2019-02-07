/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: EmulatorManager
 * Role: Determins the connected devices on the host machine, assigns and manages session tokens for each device
 * Description:
 */
package gr.aueb.android.barista.emulator;

import gr.aueb.android.barista.emulator.adb.ADBClient;
import gr.aueb.android.barista.rest.dto.WmSizeDTO;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class EmulatorManager {

    private static EmulatorManager INSTANCE = null;
    private final String DEFAULT_EMULATOR_STORAGE_PATH = "/storage/emulated/0";
    ArrayList<String> connectedDevices;
    Hashtable<String,String> emulatorTokens;

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
            BaristaLogger.print("Creating Emulator Managar");
            INSTANCE = new EmulatorManager();
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
     * Runs the adb devices command to list all devices. Then forms an ArrayList containing the devices ids retrieved
     * @return The list containing all the ids of the connected devices in String format
     */
    public ArrayList<String> getConnectedDevices(){
        BaristaLogger.print("Geting all connected devices");
        ArrayList<String> result = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec("adb devices"); // list connected devices

            BufferedReader stdOut =  new BufferedReader(new InputStreamReader(p.getInputStream())); // get the output stream

            String line = null;
            stdOut.readLine(); // eat the first row

            while((line = stdOut.readLine()) != null){          //parse lines of output stream
                if(!line.isEmpty()) {                                      // expected output is 'emulator-xxxx device'.
                    String deviceId = line.split("\t")[0];    // get only 'emulator-xxxx' part
                    BaristaLogger.print("Device found: "+deviceId);
                    result.add(deviceId);
                }
            }

            return result;

        } catch (IOException e) {
            BaristaLogger.print("Error listing connected devices.");
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
                BaristaLogger.print("Error generating session token file on host machine. "+e.getMessage());

                e.printStackTrace();
            }

            //push the file to the emulator
            this.pushFile(emulatorID,tokenFileName,this.DEFAULT_EMULATOR_STORAGE_PATH);

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
            BaristaLogger.print("Error trying to push session token to target device. "+e.getMessage());
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

    /**
     * Returns the Override size as returned from the 'adb -s [emulatorId] shell wm size'.
     * Mainly used for testing purposes.
     * @param emulatorID The emulatorId
     * @return A WmSizeDTO {@See WmSizeDTO.If not overided size is found null is returned.
     *          The SizeDto instance will contain only width and height properties
     */
    public WmSizeDTO getOverrideSize(String emulatorID){
        BaristaLogger.print("Calling getActualSize");
        try {
            WmSizeDTO currentScreen = new WmSizeDTO();

            // build command
            ProcessBuilder pb = new ProcessBuilder("adb", "-s", emulatorID,"shell","wm","size");

            //execute command
            Process p = pb.start();

            //read the output
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line ="";
            while( (line = output.readLine()) !=null){

                // parse lines like 'Override size: ...'
                if(line.matches("Override size:(.*)")){

                    // get only the WxH part
                    String sizeString = line.split(" ")[2];
                    //exract width (first number, left of x)
                    int width = Integer.parseInt(sizeString.split("x")[0]);
                    //exract width (second number, right of x)
                    int height = Integer.parseInt(sizeString.split("x")[1]);
                    currentScreen.setWidth(width);
                    currentScreen.setHeight(height);

                }
            }
            BaristaLogger.print("RETURNING: "+currentScreen.getWidth()+"x"+currentScreen.getHeight());
            return currentScreen;
        } catch (IOException e) {
            e.printStackTrace();
            //todo delete
            BaristaLogger.print("NO EMULATOR FOUND");
            return null;
        }

    }

    public static void revalidate(){
        INSTANCE = new EmulatorManager();
    }

}
