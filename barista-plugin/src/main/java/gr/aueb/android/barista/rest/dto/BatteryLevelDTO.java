/**
 * Author: Tsiskomichelis Stelios
 * Created On: 27/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: BatteryLevelDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.rest.dto;


import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.BatteryLevel;
import gr.aueb.android.barista.rest.mapper.CommandMapperImpl;

@JsonTypeName("BatteryLevel")
public class BatteryLevelDTO extends CommandDTO<BatteryLevel>{

    private int level;

    public BatteryLevelDTO(){

    }

    public BatteryLevelDTO(String sessionToken, int level) {
        super(sessionToken);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public BatteryLevel toDomainObject() {
        return CommandMapperImpl.INSTANCE.fromBatteryLevelDTO(this);
    }
}