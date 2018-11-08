/**
 * Author: Tsiskomichelis Stelios
 * Created On: 4/11/2018
 * Project: barista-plugin
 * <p>
 * ClassName: BaristaConfigurationExtension
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core;

public class BaristaConfigurationExtension {
    //default port value
    private int port = 8040;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
