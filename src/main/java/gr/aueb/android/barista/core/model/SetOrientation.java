package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class SetOrientation extends AbstractAdbCommand {

    /**
     *  Orientation option is an integer representing the four possible positions.
     *  https://developer.android.com/reference/android/view/Surface.html#ROTATION_0
     *  0 :  0 degree rotation.
     *  1: 90 degree rotation.
     *  2: 180 degree rotation.
     *  3: 270 degree rotation.
     *
     */
    private int orientation;

    public SetOrientation(){


    }

    public SetOrientation(String sessionToken, int orientation) {
        super(sessionToken);
        this.orientation = orientation;
    }


    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.SET_ORIENTATION).append(orientation);
        String command = buffer.toString();
        return command;
    }

    @Override
    public boolean isCompleted(CommandClient client){
        GetOrientation getOrientation = new GetOrientation(this.getSessionToken());
        client.executeCommand(getOrientation);
        return (getOrientation.getOrientationValue() == this.orientation);
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
