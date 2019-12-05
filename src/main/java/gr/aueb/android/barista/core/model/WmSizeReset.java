/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmSizeReset
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class WmSizeReset extends AbstractAdbCommand {

    public WmSizeReset(){

    }

    public WmSizeReset(String sessionToken){
        super(sessionToken);
    }

    @Override
    public String getCommandString() {
       return BaristaCommandPrefixes.WM_SIZE_RESET;
    }

}
