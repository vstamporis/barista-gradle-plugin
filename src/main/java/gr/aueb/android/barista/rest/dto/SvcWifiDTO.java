/**
 * Author: Tsiskomichelis Stelios
 * Created On: 1/3/2019
 * Project: barista-plugin
 * <p>
 * ClassName: SvcWifiDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.SvcWifi;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

@JsonTypeName("SvcWifi")
public class SvcWifiDTO extends CommandDTO<SvcWifi> {

    private boolean enabled;

    public SvcWifiDTO() {

    }

    public SvcWifiDTO(String sessionToken, boolean enabled) {
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
    public SvcWifi toDomainObject() {
        return CommandMapper.INSTANCE.fromSvcWifiDTO(this);
    }
}
