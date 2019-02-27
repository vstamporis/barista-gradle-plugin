/**
 * Author: Tsiskomichelis Stelios
 * Created On: 27/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: ModelDataHelper
 * Role:
 * Description:
 */
package gr.aueb.android.barista.helpers;

import gr.aueb.android.barista.core.model.BatteryCharge;
import gr.aueb.android.barista.core.model.BatteryLevel;
import gr.aueb.android.barista.core.model.PmGrant;
import gr.aueb.android.barista.core.model.WmSize;

public class ModelDataHelper {

    public static final PmGrant grantCommand = new PmGrant(ConstantValues.sessionToken,ConstantValues.permission);
    public static final BatteryLevel batteryLevelCommand = new BatteryLevel(ConstantValues.sessionToken, ConstantValues.level);
    public static final BatteryCharge batteryChargeCommand = new BatteryCharge(ConstantValues.sessionToken,ConstantValues.isPlugged);
}
