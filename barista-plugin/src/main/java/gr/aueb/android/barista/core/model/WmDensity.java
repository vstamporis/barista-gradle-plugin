package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class WmDensity extends AbstractAdbCommand {

    private int density;


    public WmDensity() {
    }

    /**
     *
     * @param sessionToken
     * @param density
     */
    public WmDensity(String sessionToken, int density) {
        super(sessionToken);
        this.density = density;
    }

    @Override
    public String getCommandString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.WM_DENSITY).append(" ").append(density);
        String command = buffer.toString();
        return command;

    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }
}
