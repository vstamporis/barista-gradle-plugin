package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

public class WmSize extends AbstractAdbCommand {

    int width;
    int height;

    boolean reset;

    DimensionUnit unit = DimensionUnit.PIXEL;


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
        buffer.append(BaristaCommandPrefixes.WM_SIZE)
                .append(" ")
                .append(Integer.toString(width))
                .append("x")
                .append(Integer.toString(height));

        String command = buffer.toString();
        return command;
    }

    @Override
    /**
     *  Verifies the sucessful execution of the size command by asseryting the actual emulator size using WmGetSize Commant
     *  @see gr.aueb.android.barista.core.model.WmGetSize WmGetSize
     *
     */
    public boolean isCompleted(CommandClient client){
        BaristaLogger.print("Checking for successfully execution of command. ");
        WmGetSize getSize = new WmGetSize(this.getSessionToken());
        client.executeCommand(getSize);

        return (getSize.getWidth() == this.width & getSize.getHeight() == this.height);

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
