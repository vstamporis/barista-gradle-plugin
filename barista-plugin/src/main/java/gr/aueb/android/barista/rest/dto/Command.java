package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
        @JsonSubTypes.Type(value = GeoFix.class),
        @JsonSubTypes.Type(value = WmSize.class),
        @JsonSubTypes.Type(value = WmDensity.class),
})
public abstract class Command {

    public Command(){

    }

    public Command(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    private String sessionToken;

    public String getSessionToken() {
        return sessionToken;
    }

}
