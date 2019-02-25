package gr.aueb.android.barista.rest.mapper;

import gr.aueb.android.barista.core.model.*;
import gr.aueb.android.barista.rest.dto.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommandMapper {

    CommandMapper INSTANCE = Mappers.getMapper(CommandMapper.class);

    GeoFix fromGeoFixDTO(GeoFixDTO geoFixDTO);

    WmSize fromWmSizeDTO(WmSizeDTO wmSizeDTO);

    WmDensity fromWmDensityDTO(WmDensityDTO wmDensityDTO);

    WmSizeReset fromWmSizeResetDTO(WmSizeResetDTO wmSizeResetDTO);

    PmGrant fromPmGrantDTO(PmGrantDTO pmGrantDTO);

}
