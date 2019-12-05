/**
 * Author: Tsiskomichelis Stelios
 * Created On: 2/5/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmDensityReset
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class WmDensityReset extends AbstractAdbCommand {

    public WmDensityReset(){

    }

    public WmDensityReset(String sessionToken){
        super(sessionToken);
    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.WM_DENSITY_RESET;
    }


}
