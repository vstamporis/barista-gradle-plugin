package gr.aueb.android.barista.core.model;

public class GeoFix extends AbstractCommand {

    private double latitude;
    private double longitude;

    public GeoFix(){

    }

    @Override
    public String getCommandString() {
        return null;
    }

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
