package gr.aueb.android.barista.core.model;

public class WmDensity extends AbstractAdbCommand {

    private int density;
    private static String WM_DENSITY = "shell wm density";

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
        return null;

    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }
}
