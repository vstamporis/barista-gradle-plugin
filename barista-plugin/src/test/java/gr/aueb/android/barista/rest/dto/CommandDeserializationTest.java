package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.aueb.android.barista.rest.dto.CommandDTO;
import gr.aueb.android.barista.rest.dto.GeoFixDTO;
import gr.aueb.android.barista.rest.dto.WmSizeDTO;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommandDeserializationTest {

    GeoFixDTO geoFix;
    WmSizeDTO wmSize;
    WmSizeResetDTO wmSizeReset;

    String geoFixJson = "{\"type\":\"GeoFix\",\"sessionToken\":\"1\",\"latitude\":1.0,\"longitude\":2.0}";
    String wmSizeJson = "{\"type\":\"WmSize\",\"sessionToken\":\"2\",\"width\":1280,\"height\":800,\"reset\":false,\"unit\":\"DPI\"}";
    String wmSizeResetJson = "{\"type\":\"WmSizeReset\",\"sessionToken\":\"3\"}";

    ObjectMapper objectMapper;

    @Before
    public void setup(){

        geoFix = new GeoFixDTO("1", 1.0, 2.0);
        wmSize = new WmSizeDTO("2", 1280, 800, false, "DPI");
        wmSizeReset = new WmSizeResetDTO("3");

        objectMapper = new ObjectMapper();

    }

    @Test
    public void testPolymorphicDeserialization() throws IOException {

        assertThat(objectMapper.writeValueAsString(geoFix), is(equalTo(geoFixJson)));
        assertThat(objectMapper.writeValueAsString(wmSize), is(equalTo(wmSizeJson)));

        CommandDTO geofixCommand = objectMapper.readValue(geoFixJson, CommandDTO.class);
        assertThat(geofixCommand instanceof GeoFixDTO, is(true));

        CommandDTO wmSizeCommand = objectMapper.readValue(wmSizeJson, CommandDTO.class);
        assertThat(wmSizeCommand instanceof WmSizeDTO, is(true));

        CommandDTO wmSizeResetCommand = objectMapper.readValue(wmSizeResetJson, CommandDTO.class);
        assertThat(wmSizeCommand instanceof WmSizeDTO, is(true));


    }



}
