package gr.aueb.android.barista.core.model;

public class WmSize extends AbstractCommand {

    int width;
    int height;
    boolean reset;
    DimensionUnit unit = DimensionUnit.PIXEL;

    public WmSize(){

    }

    @Override
    public String getCommandString() {
        return null;
    }

    public WmSize(String sessionToken, int width, int height, boolean reset, DimensionUnit unit) {
        super(sessionToken);
        this.width = width;
        this.height = height;
        this.reset = reset;
        this.unit = unit;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public DimensionUnit getUnit() {
        return unit;
    }

    public void setUnit(DimensionUnit unit) {
        this.unit = unit;
    }
}
