package gr.aueb.android.barista.rest.mapper;

import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.rest.dto.GeoFixDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommandMapper {

    CommandMapper INSTANCE = Mappers.getMapper(CommandMapper.class);

    GeoFix fromGeoFixDTO(GeoFixDTO geoFixDTO);

}
