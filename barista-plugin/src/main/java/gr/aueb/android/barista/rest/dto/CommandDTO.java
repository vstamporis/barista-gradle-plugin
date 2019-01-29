package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.core.model.WmDensity;
import gr.aueb.android.barista.core.model.WmSize;

/**
 * Mapping with jackson
 * https://www.baeldung.com/jackson-inheritance
 * https://github.com/FasterXML/jackson-docs/wiki/JacksonPolymorphicDeserialization
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GeoFixDTO.class),
        @JsonSubTypes.Type(value = WmSizeDTO.class),
        @JsonSubTypes.Type(value = WmDensityDTO.class),
})
public abstract class CommandDTO<T extends Command> {



    public CommandDTO(){

    }

    private String sessionToken;

    public CommandDTO(String sessionToken) {
        this.sessionToken = sessionToken;
    }


    public String getSessionToken() {
        return sessionToken;
    }

    public abstract T toDomainObject();

}
