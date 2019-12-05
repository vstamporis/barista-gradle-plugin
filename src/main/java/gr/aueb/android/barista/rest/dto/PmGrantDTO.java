/**
 * Author: Tsiskomichelis Stelios
 * Created On: 19/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: PmGrantDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.PmGrant;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

@JsonTypeName("PmGrant")
public class PmGrantDTO extends CommandDTO<PmGrant> {

    private String permission;

    /**
     * Default empty constructor
     */
    public PmGrantDTO(){

    }

    public PmGrantDTO(String sessionID, String permission){
        super(sessionID);
        this.permission = permission;
    }

    @Override
    public PmGrant toDomainObject() {
        return CommandMapper.INSTANCE.fromPmGrantDTO(this);
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
