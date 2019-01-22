package gr.aueb.android.barista.rest.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.aueb.android.barista.DataHelper;
import gr.aueb.android.barista.core.model.DimensionUnit;
import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.core.model.WmSize;
import gr.aueb.android.barista.rest.dto.CommandDTO;
import gr.aueb.android.barista.rest.dto.GeoFixDTO;
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
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws IOException {

        objectMapper = new ObjectMapper();
        geoFixDTO = (GeoFixDTO) objectMapper.readValue(DataHelper.GEOFIX_JSON, CommandDTO.class);
        wmSizeDTO = (WmSizeDTO) objectMapper.readValue(DataHelper.WM_SIZE_JSON, CommandDTO.class);
    }

    @Test
    public void testDtoToModelConversion(){

        GeoFix geoFix = CommandMapper.INSTANCE.fromGeoFixDTO(geoFixDTO);
        assertThat(geoFix, is(not(nullValue())));

        WmSize wmSize = CommandMapper.INSTANCE.fromWmSizeDTO(wmSizeDTO);
        assertThat(wmSize, is(not(nullValue())));
        assertThat(wmSize.getUnit(), is(equalTo(DimensionUnit.DPI)));

    }

}
