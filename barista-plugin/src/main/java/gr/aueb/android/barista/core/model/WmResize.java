/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmResize
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

public class WmResize extends AbstractAdbCommand {

    public WmResize(String sessionToken){
        super(sessionToken);
    }

    @Override
    public String getCommandString() {
       return "shell wm size reset";
    }
}
