package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

@JsonTypeName("GeoFix")
public class GeoFixDTO extends CommandDTO<GeoFix> {

    private double latitude;
    private double longitude;

    public GeoFixDTO(){

    }

    @Override
    public GeoFix toDomainObject() {
        return CommandMapper.INSTANCE.fromGeoFixDTO(this);
    }

    public GeoFixDTO(String sessionToken, double latitude, double longitude) {
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
