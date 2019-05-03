/**
 * Author: Tsiskomichelis Stelios
 * Created On: 2/5/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmDensitySizeResetDTO
 * Role:
 * Description:
 */
package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.WmDensityReset;

@JsonTypeName("WmDensityReset")
public class WmDensitySizeResetDTO extends CommandDTO<WmDensityReset>{

    @Override
    public WmDensityReset toDomainObject() {
        return null;
    }
}
