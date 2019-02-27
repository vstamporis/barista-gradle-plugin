/**
 * Author: Tsiskomichelis Stelios
 * Created On: 27/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: BatteryChargeDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.BatteryCharge;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

@JsonTypeName("BatteryCharge")
public class BatteryChargeDTO extends CommandDTO<BatteryCharge> {

    private boolean isPlugged;

    public BatteryChargeDTO() {
    }

    public BatteryChargeDTO(String sessionToken, boolean isPlugged) {
        super(sessionToken);
        this.isPlugged = isPlugged;
    }

    @Override
    public BatteryCharge toDomainObject() {
        return CommandMapper.INSTANCE.fromBatteryChargeDTO(this);
    }

    public boolean isPlugged() {
        return isPlugged;
    }

    public void setPlugged(boolean plugged) {
        isPlugged = plugged;
    }
}
