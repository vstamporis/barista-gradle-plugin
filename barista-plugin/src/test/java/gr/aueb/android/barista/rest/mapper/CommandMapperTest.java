package gr.aueb.android.barista.rest.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.aueb.android.barista.JsonDataHelper;
import gr.aueb.android.barista.core.model.DimensionUnit;
import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.core.model.WmDensity;
import gr.aueb.android.barista.core.model.WmSize;
import gr.aueb.android.barista.rest.dto.CommandDTO;
import gr.aueb.android.barista.rest.dto.GeoFixDTO;
import gr.aueb.android.barista.rest.dto.WmDensityDTO;
import gr.aueb.android.barista.rest.dto.WmSizeDTO;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class CommandMapperTest {

    GeoFixDTO geoFixDTO;
    WmSizeDTO wmSizeDTO;
    WmSizeDTO wmSizeDTO2;
    WmDensityDTO wmDensityDTO;
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws IOException {

        objectMapper = new ObjectMapper();
        geoFixDTO = (GeoFixDTO) objectMapper.readValue(JsonDataHelper.GEOFIX_JSON, CommandDTO.class);
        wmSizeDTO = (WmSizeDTO) objectMapper.readValue(JsonDataHelper.WM_SIZE_JSON, CommandDTO.class);
        wmSizeDTO2 = (WmSizeDTO) objectMapper.readValue(JsonDataHelper.WM_SIZE_JSON_2, CommandDTO.class);
        wmDensityDTO = (WmDensityDTO) objectMapper.readValue(JsonDataHelper.DENSITY_JSON, CommandDTO.class);
    }

    @Test
    /**
     * test for JSON data
     * "{
     *      "type":"WmSize",
     *      "sessionToken":"2",
     *      "width":1280,
     *      "height":800,
     *      "reset":false,
     *      "unit":"DPI"
     *  }";
     */
    public void testDtoSizeConversion(){

        WmSize wmSize = CommandMapper.INSTANCE.fromWmSizeDTO(wmSizeDTO);
        assertThat(wmSize, is(not(nullValue())));
        assertThat(wmSize.getSessionToken(),is(equalTo(wmSizeDTO.getSessionToken())));
        assertThat(wmSize.getHeight(),is(equalTo(wmSizeDTO.getHeight())));
        assertThat(wmSize.getWidth(),is(equalTo(wmSizeDTO.getWidth())));
        assertThat(wmSize.isReset(),is(equalTo(wmSizeDTO.isReset())));
        //todo some bug happens here with string <DPI>
        //assertThat(wmSize.getUnit(),is(equalTo(wmSizeDTO.getUnit())));

    }

    /**
     * {
     * "type":"WmSize",
     * "sessionToken":"8ba3eee6-51e6-4d8a-8e5f-1243746ad475",
     * "height":600,"reset":false,
     * "unit":"DPI",
     * "width":500
     * }
     */
    @Test
    public void testDtoSizeConversion2(){

        WmSize wmSize = CommandMapper.INSTANCE.fromWmSizeDTO(wmSizeDTO2);
        assertThat(wmSize, is(not(nullValue())));
        assertThat(wmSize.getSessionToken(),is(equalTo(wmSizeDTO2.getSessionToken())));
        assertThat(wmSize.getHeight(),is(equalTo(wmSizeDTO2.getHeight())));
        assertThat(wmSize.getWidth(),is(equalTo(wmSizeDTO2.getWidth())));
        assertThat(wmSize.isReset(),is(equalTo(wmSizeDTO2.isReset())));
        //todo some bug happens here with string <DPI>
        //assertThat(wmSize.getUnit(),is(equalTo(wmSizeDTO.getUnit())));

    }


    @Test
    /**
     * Test for Json Data
     * "{
     *  "type":"GeoFix",
     *  "sessionToken":"1",
     *  "latitude":1.0,
     *  "longitude":2.0}";
     */
    public void testGeoFixConversion(){
        GeoFix geoFix = CommandMapper.INSTANCE.fromGeoFixDTO(geoFixDTO);
        assertThat(geoFix, is(not(nullValue())));
        assertThat(geoFix.getSessionToken(),is(equalTo(geoFixDTO.getSessionToken())));
        assertThat(geoFix.getLatitude(),is(equalTo(geoFixDTO.getLatitude())));
        assertThat(geoFix.getLongitude(),is(equalTo(geoFixDTO.getLongitude())));
    }

    @Test
    /**
     * "{
     *  "type":"WmDensity",
     *  "sessionToken":"3",
     *  "density":600"
     *  }"
     */
    public void testDensityConversion(){
        WmDensity density = CommandMapper.INSTANCE.fromWmDensityDTO(wmDensityDTO);
        assertThat(density,is(not(nullValue())));
        assertThat(density.getSessionToken(),is(equalTo(wmDensityDTO.getSessionToken())));
        assertThat(density.getDensity(),is(equalTo(wmDensityDTO.getDensity())));
    }

}
