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

import java.io.Serializable;

public class BaristaConfigurationExtension implements Serializable {
    //default port value
    private Integer port = 8040;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String toString(){
        return "Port:"+port.toString();
    }
}
