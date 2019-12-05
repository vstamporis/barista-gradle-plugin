package gr.aueb.android.barista.rest.mapper;

import com.android.tools.build.jetifier.core.config.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.aueb.android.barista.helpers.ConstantValues;
import gr.aueb.android.barista.helpers.JsonDataHelper;
import gr.aueb.android.barista.core.model.*;
import gr.aueb.android.barista.helpers.ModelDataHelper;
import gr.aueb.android.barista.rest.dto.*;
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
    WmSizeResetDTO wmSizeResetDTO;
    PmGrantDTO grantDTO;
    PmRevokeDTO revokeDTO;
    BatteryLevelDTO batteryLevelDTO;
    BatteryChargeDTO batteryChargeDTO;
    SvcWifiDTO wifiDTO;
    SvcDataDTO dataDTO;
    SetOrientationDTO orientationDTO;

    private ObjectMapper objectMapper;

    @Before
    public void setup() throws IOException {

        objectMapper = new ObjectMapper();
        geoFixDTO = (GeoFixDTO) objectMapper.readValue(JsonDataHelper.GEOFIX_JSON, CommandDTO.class);
        wmSizeDTO = (WmSizeDTO) objectMapper.readValue(JsonDataHelper.WM_SIZE_JSON, CommandDTO.class);
        wmSizeDTO2 = (WmSizeDTO) objectMapper.readValue(JsonDataHelper.WM_SIZE_JSON_2, CommandDTO.class);
        wmDensityDTO = (WmDensityDTO) objectMapper.readValue(JsonDataHelper.DENSITY_JSON, CommandDTO.class);
        wmSizeResetDTO = (WmSizeResetDTO) objectMapper.readValue(JsonDataHelper.RESET_JSON, CommandDTO.class);
        grantDTO = (PmGrantDTO) objectMapper.readValue(JsonDataHelper.GRANT_JSON, CommandDTO.class);
        revokeDTO = (PmRevokeDTO) objectMapper.readValue(JsonDataHelper.REVOKE_JSON, CommandDTO.class);
        batteryLevelDTO = (BatteryLevelDTO) objectMapper.readValue(JsonDataHelper.BATTERY_LVL_JSON, CommandDTO.class);
        batteryChargeDTO = (BatteryChargeDTO) objectMapper.readValue(JsonDataHelper.BATTERY_CHARGE_JSON,CommandDTO.class);
        wifiDTO = (SvcWifiDTO) objectMapper.readValue(JsonDataHelper.WIFI_JSON,CommandDTO.class);
        dataDTO = (SvcDataDTO) objectMapper.readValue(JsonDataHelper.DATA_JSON,CommandDTO.class);
        orientationDTO = (SetOrientationDTO) objectMapper.readValue(JsonDataHelper.ORIENTATION_JSON, CommandDTO.class);
    }

    @Test
    public void testDtoSizeConversion(){

        WmSize wmSize = CommandMapper.INSTANCE.fromWmSizeDTO(wmSizeDTO);
        assertThat(wmSize, is(not(nullValue())));
        assertThat(wmSize.getSessionToken(),is(equalTo(wmSizeDTO.getSessionToken())));
        assertThat(wmSize.getHeight(),is(equalTo(wmSizeDTO.getHeight())));
        assertThat(wmSize.getWidth(),is(equalTo(wmSizeDTO.getWidth())));
        assertThat(wmSize.isReset(),is(equalTo(wmSizeDTO.isReset())));
        assertThat(wmSize.getCommandString(), is(equalTo(ModelDataHelper.sizeCommand.getCommandString())));

    }


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
    public void testGeoFixConversion(){
        GeoFix geoFix = CommandMapper.INSTANCE.fromGeoFixDTO(geoFixDTO);
        assertThat(geoFix, is(not(nullValue())));
        assertThat(geoFix.getSessionToken(),is(equalTo(geoFixDTO.getSessionToken())));
        assertThat(geoFix.getLatitude(),is(equalTo(geoFixDTO.getLatitude())));
        assertThat(geoFix.getLongitude(),is(equalTo(geoFixDTO.getLongitude())));
    }

    @Test
    public void testDensityConversion(){
        WmDensity density = CommandMapper.INSTANCE.fromWmDensityDTO(wmDensityDTO);
        assertThat(density,is(not(nullValue())));
        assertThat(density.getSessionToken(),is(equalTo(wmDensityDTO.getSessionToken())));
        assertThat(density.getDensity(),is(equalTo(wmDensityDTO.getDensity())));
        assertThat(density.getCommandString(),is(equalTo(ModelDataHelper.densityCommand.getCommandString())));
    }


    @Test
    public void testSizeResetConversion(){
        WmSizeReset reset = CommandMapper.INSTANCE.fromWmSizeResetDTO(wmSizeResetDTO);
        assertThat(reset,is(not(nullValue())));
        assertThat(reset.getSessionToken(),is(equalTo(wmSizeResetDTO.getSessionToken())));

    }

    @Test
    public void testPmGrantConversion(){
        PmGrant  grant = CommandMapper.INSTANCE.fromPmGrantDTO(grantDTO);
        assertThat(grant,is(not(nullValue())));
        assertThat(grant.getPermission(),is(equalTo(ConstantValues.permission)));
        assertThat(grant.getCommandString(),is(equalTo(ModelDataHelper.grantCommand.getCommandString())));

    }

    @Test
    public void testBatteryLevelConversion(){
        BatteryLevel level = CommandMapper.INSTANCE.fromBatteryLevelDTO(batteryLevelDTO);
        assertThat(level,is(not(nullValue())));
        assertThat(level.getLevel(),is(equalTo(ConstantValues.level)));
        assertThat(level.getCommandString(),is(equalTo(ModelDataHelper.batteryLevelCommand.getCommandString())));
    }

    @Test
    public void testBatteryChargeConversion(){
        BatteryCharge charge = CommandMapper.INSTANCE.fromBatteryChargeDTO(batteryChargeDTO);
        assertThat(charge,is(not(nullValue())));
        assertThat(charge.isPlugged(),is(equalTo(ConstantValues.isPlugged)));
        assertThat(charge.getCommandString(),is(equalTo(ModelDataHelper.batteryChargeCommand.getCommandString())));
    }

    @Test
    public void testWifiConversion(){
        SvcWifi wifi = CommandMapper.INSTANCE.fromSvcWifiDTO(wifiDTO);
        assertThat(wifi,is(not(nullValue())));
        assertThat(wifi.isEnabled(),is(equalTo(ConstantValues.wifiEnable)));
        assertThat(wifi.getCommandString(),is(equalTo(ModelDataHelper.wifiCommand.getCommandString())));
    }

    @Test
    public void testDataConversion(){
        SvcData data = CommandMapper.INSTANCE.fromSvcDataDTO(dataDTO);
        assertThat(data,is(not(nullValue())));
        assertThat(data.isEnabled(),is(equalTo(ConstantValues.dataEnable)));
        assertThat(data.getCommandString(),is(equalTo(ModelDataHelper.dataCommand.getCommandString())));
    }

    @Test
    public void testRevokePermisisonConversion(){
        PmRevoke cmd = CommandMapper.INSTANCE.fromPmRevokeDTO(revokeDTO);
        assertThat(cmd,is(not(nullValue())));
        assertThat(cmd.getPermission(),is(equalTo(ConstantValues.permission)));
        assertThat(cmd.getCommandString(),is(equalTo(ModelDataHelper.revokeCommand.getCommandString())));
    }

    @Test
    public void testSetOrientationConversion(){
        SetOrientation cmd = CommandMapper.INSTANCE.fromSetOrientationDTO(orientationDTO);
        assertThat(cmd,is(not(nullValue())));
        assertThat(cmd.getOrientation(),is(equalTo(ConstantValues.orientation_90)));
        assertThat(cmd.getCommandString(),is(equalTo(ModelDataHelper.orientationCommand.getCommandString())));

    }

}
