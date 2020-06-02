package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

@JsonTypeName("GeoFix")
public class GeoFix extends AbstractTelnetCommand {

    private double latitude;
    private double longitude;

    public GeoFix(){

    }

    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.GEO_FIX)
                .append(" ")
                .append(Double.toString(longitude))
                .append(" ")
                .append(Double.toString(latitude))
                .append("\r\n");

        String command = buffer.toString();//.replace('.', ',');
        return command;
    }

    /**
     *
     * @param sessionToken
     * @param latitude
     * @param longitude
     */
    public GeoFix(String sessionToken, double latitude, double longitude) {
        super(sessionToken);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
