/**
 * Author: Tsiskomichelis Stelios
 * Created On: 9/4/2019
 * Project: barista-plugin
 * <p>
 * ClassName: PmRevoke
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.emulator.EmulatorManager;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;

public class PmRevoke extends AbstractAdbCommand {

    private String permission;

    public PmRevoke(){

    }

    public PmRevoke(String sessionToken, String permission) {
        super(sessionToken);
        this.permission = permission;
    }

    @Override
    public String getCommandString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(BaristaCommandPrefixes.PM_REVOKE)
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
