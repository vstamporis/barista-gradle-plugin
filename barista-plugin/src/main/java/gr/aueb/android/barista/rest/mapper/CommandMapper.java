package gr.aueb.android.barista.rest.mapper;

import gr.aueb.android.barista.core.model.*;
import gr.aueb.android.barista.rest.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommandMapper {

    CommandMapper INSTANCE = Mappers.getMapper(CommandMapper.class);

    GeoFix fromGeoFixDTO(GeoFixDTO geoFixDTO);

    WmSize fromWmSizeDTO(WmSizeDTO wmSizeDTO);

    WmDensity fromWmDensityDTO(WmDensityDTO wmDensityDTO);

    WmSizeReset fromWmSizeResetDTO(WmSizeResetDTO wmSizeResetDTO);

    PmGrant fromPmGrantDTO(PmGrantDTO pmGrantDTO);

    PmRevoke fromPmRevokeDTO(PmRevokeDTO pmRevokeDTO);

    BatteryLevel fromBatteryLevelDTO(BatteryLevelDTO batteryLevelDTO);

    BatteryCharge fromBatteryChargeDTO(BatteryChargeDTO batteryChargeDTO);

    SvcData fromSvcDataDTO(SvcDataDTO svcDataDTO);

    SvcWifi fromSvcWifiDTO(SvcWifiDTO svcWifiDTO);


}
