package gr.aueb.android.barista.rest.mapper;

import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.core.model.WmDensity;
import gr.aueb.android.barista.core.model.WmSize;
import gr.aueb.android.barista.rest.dto.GeoFixDTO;
import gr.aueb.android.barista.rest.dto.WmDensityDTO;
import gr.aueb.android.barista.rest.dto.WmSizeDTO;
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

}
