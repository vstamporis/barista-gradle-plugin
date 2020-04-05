/**
 * Author: Tsiskomichelis Stelios
 * Created On: 19/2/2019
 * Project: barista-plugin
 * <p>
 * ClassName: PmGrant
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.core.emulator.EmulatorManager;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class PmGrant extends AbstractAdbCommand {

    private String permission;

    /**
     * Default Constructor
     */
    public PmGrant(){

    }

    public PmGrant(String sessionToken,String permission){
        super(sessionToken);
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.PM_GRANT)
                .append(" ")
                // todo check if this can be writen better
                // the package name is provided staticaly by the emulator manager.
                // maybe is better to be given from the client
                .append(EmulatorManager.getPackageName())
                .append(" ")
                .append(this.permission);

        String command = buffer.toString();
        return command;
    }


}
