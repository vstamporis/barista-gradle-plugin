/**
 * Author: Tsiskomichelis Stelios
 * Created On: 2/5/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmDensityResetDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.WmDensityReset;
import gr.aueb.android.barista.server.mapper.CommandMapper;


@JsonTypeName("WmDensityReset")
public class WmDensityResetDTO extends CommandDTO<WmDensityReset>{

    public WmDensityResetDTO(){

    }

    public WmDensityResetDTO(String sessionToken){
        super(sessionToken);
    }


    @Override
    public WmDensityReset toDomainObject() {
        return CommandMapper.INSTANCE.fromWmDensityResetDTO(this);
    }
}
