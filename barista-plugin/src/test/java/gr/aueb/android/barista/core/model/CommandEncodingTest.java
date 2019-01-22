package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommandEncodingTest {

    GeoFix geoFix;
    WmSize wmSize;

    String geoFixJson = "{\"type\":\"GeoFixDTO\",\"sessionToken\":\"1\",\"latitude\":1.0,\"longitude\":2.0}";
    String wmSizeJson = "{\"type\":\"WmSizeDTO\",\"sessionToken\":\"2\",\"width\":1280,\"height\":800,\"reset\":false,\"unit\":\"DPI\"}";

    ObjectMapper objectMapper;

    @Before
    public void setup(){

        geoFix = new GeoFix("1", 1.0, 2.0);
        wmSize = new WmSize("2", 1280, 800, false, DimensionUnit.DPI);

        objectMapper = new ObjectMapper();

    }

    @Test
    public void testPoly() throws IOException {

        assertThat(objectMapper.writeValueAsString(geoFix), is(equalTo(geoFixJson)));
        assertThat(objectMapper.writeValueAsString(wmSize), is(equalTo(wmSizeJson)));

        AbstractCommand geofixCommand = objectMapper.readValue(geoFixJson, AbstractCommand.class);
        assertThat(geofixCommand instanceof GeoFix, is(true));

        AbstractCommand wmSizeCommand = objectMapper.readValue(wmSizeJson, AbstractCommand.class);
        assertThat(wmSizeCommand instanceof WmSize, is(true));

    }



}
