package gr.aueb.android.barista.core.model;

public class WmSize extends AbstractAdbCommand {

    int width;
    int height;

    boolean reset;
    DimensionUnit unit = DimensionUnit.PIXEL;
    public static final String WM_SIZE = "shell wm size";

    public WmSize(){

    }


    /**
     *
     * @param sessionToken
     * @param width
     * @param height
     * @param reset
     * @param unit
     */
    public WmSize(String sessionToken, int width, int height, boolean reset, DimensionUnit unit) {
        super(sessionToken);
        this.width = width;
        this.height = height;
        this.reset = reset;
        this.unit = unit;
    }

    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(WM_SIZE)
                .append(" ")
                .append(Integer.toString(width))
                .append("x")
                .append(Integer.toString(height));

        String command = buffer.toString();
        return command;
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
