package gr.aueb.android.barista.server.mapper;

import gr.aueb.android.barista.core.model.*;
import gr.aueb.android.barista.server.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *  Command Mapper interface provides all the methods required to transform a DTO object to a model Object
 *  After the conversion from JSON to DTO, the DTOs must be transformed into model objects. This is done by
 *  Mapstruct framework that uses the following maping.
 */
@Mapper
public interface CommandMapper {

    /**
     *  Expose CommanderMapper function throught this instance.
     */
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

    WmDensityReset fromWmDensityResetDTO(WmDensityResetDTO wmDensityResetDTO);

    SetOrientation fromSetOrientationDTO(SetOrientationDTO setOrientationDTO);

    GpsState fromGPSDDTO(GpsStatusDTO GPSDTO);

    Monkey fromMonkeyDTO(MonkeyDTO monkeyDTO);
}
