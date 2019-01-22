package gr.aueb.android.barista.core.model;

public class WmDensity extends AbstractCommand {

    private int density;

    public WmDensity() {
    }

    @Override
    public String getCommandString() {
        return null;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }
}
