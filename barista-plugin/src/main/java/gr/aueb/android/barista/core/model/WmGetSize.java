/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmGetSize
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

public class WmGetSize extends AbstractAdbCommand {

    public WmGetSize(String sessionToken){
        super(sessionToken);
    }

    @Override
    public String getCommandString() {
        return "shell wm size";
    }
}
