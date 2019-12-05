/**
 * Author: Tsiskomichelis Stelios
 * Created On: 1/3/2019
 * Project: barista-plugin
 * <p>
 * ClassName: SvcDataDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.SvcData;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

@JsonTypeName("SvcData")
public class SvcDataDTO extends CommandDTO<SvcData> {

    private boolean enabled;

    public SvcDataDTO() {

    }

    public SvcDataDTO(String sessionToken, boolean enabled) {
        super(sessionToken);
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public SvcData toDomainObject() {
        return CommandMapper.INSTANCE.fromSvcDataDTO(this);
    }
}
