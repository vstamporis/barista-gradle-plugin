/**
 * Author: Tsiskomichelis Stelios
 * Created On: 9/4/2019
 * Project: barista-plugin
 * <p>
 * ClassName: PmRevokeDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.server.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.PmRevoke;
import gr.aueb.android.barista.server.mapper.CommandMapper;

@JsonTypeName("PmRevoke")
public class PmRevokeDTO extends CommandDTO<PmRevoke>{

    private String permission;

    /**
     * Default empty constructor
     */
    public PmRevokeDTO(){

    }

    public PmRevokeDTO(String sessionID, String permission){
        super(sessionID);
        this.permission = permission;
    }

    @Override
    public PmRevoke toDomainObject() {
       return CommandMapper.INSTANCE.fromPmRevokeDTO(this);
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
