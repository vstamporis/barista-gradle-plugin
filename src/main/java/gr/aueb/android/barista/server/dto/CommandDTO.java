package gr.aueb.android.barista.server.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gr.aueb.android.barista.core.model.Command;

/**
 * Mapping with JSON to DTO with jackson
 * https://www.baeldung.com/jackson-inheritance
 * https://github.com/FasterXML/jackson-docs/wiki/JacksonPolymorphicDeserialization
 *
 *  Each request ccontains a JSON body that describes the command to be executed.
 *  Those JSONs are transformed into a DTO objects in order to be easily manipulated by the Barsista.
 *  Jackson frameork checks the 'type' key-value pair inside those JSONs and tries to tranform them into
 *  the appropriate DTO objects. The mapping type - DTO class is provided in the annotations below.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GeoFixDTO.class),
        @JsonSubTypes.Type(value = WmSizeDTO.class),
        @JsonSubTypes.Type(value = WmDensityDTO.class),
        @JsonSubTypes.Type(value = WmSizeResetDTO.class),
        @JsonSubTypes.Type(value = WmDensityResetDTO.class),
        @JsonSubTypes.Type(value = PmGrantDTO.class),
        @JsonSubTypes.Type(value = BatteryLevelDTO.class),
        @JsonSubTypes.Type(value = BatteryChargeDTO.class),
        @JsonSubTypes.Type(value = SvcDataDTO.class),
        @JsonSubTypes.Type(value = SvcWifiDTO.class),
        @JsonSubTypes.Type(value = PmRevokeDTO.class),
        @JsonSubTypes.Type(value = SetOrientationDTO.class),
        @JsonSubTypes.Type(value = GpsStatusDTO.class)
})
public abstract class CommandDTO<T extends Command> {


    public CommandDTO(){

    }

    /**
     *  The sessionToken inside the JSON
     */
    private String sessionToken;

    /**
     *  The delay value inside the JSON
     */
    private int delay = 0;

    /**
     *
     *
     * @param sessionToken
     */
    public CommandDTO(String sessionToken) {
        this.sessionToken = sessionToken;
    }


    public String getSessionToken() {
        return sessionToken;
    }

    public int getDelay(){ return delay ; }

    public void setDelay( int delay){ this.delay = delay; }

    public abstract T toDomainObject();

}
