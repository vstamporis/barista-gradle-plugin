/**
 * Author: Tsiskomichelis Stelios
 * Created On: 4/11/2018
 * Project: barista-plugin
 * <p>
 * ClassName: BaristaConfigurationExtension
 * Role: Retrieves the configuration options provided by the user
 *
 * Description: For now, the only configuration option available to the user is the listening port of the
 *              Barista server. This class checks if the user has addded this option to the gradle configuration file
 *              and retrieves the given value. If the user hasn't assigned a specific port the default port 8040 is used.
 */
package gr.aueb.android.barista.core;

import java.io.Serializable;

public class BaristaConfigurationExtension implements Serializable {

    /**
     *  Default Barista Listening port
     */
    private Integer port = 8040;

    /**
     *  Get the value provided to the 'port' option at the configuration file
     *
     * @return The port value provided by the user
     */
    public Integer getPort() {
        return port;
    }

    /**
     *
     *
     * @param port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    public String toString(){
        return "Port:"+port.toString();
    }
}
