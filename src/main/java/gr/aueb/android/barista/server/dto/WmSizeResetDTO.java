/**
 * Author: Tsiskomichelis Stelios
 * Created On: 11/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmSizeResetDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.WmSizeReset;
import gr.aueb.android.barista.server.mapper.CommandMapper;

@JsonTypeName("WmSizeReset")
public class WmSizeResetDTO extends CommandDTO<WmSizeReset>{

    public WmSizeResetDTO(){

    }

    public WmSizeResetDTO(String sessionId){
        super(sessionId);
    }

    @Override
    public WmSizeReset toDomainObject() {
        return CommandMapper.INSTANCE.fromWmSizeResetDTO(this);
    }
}
