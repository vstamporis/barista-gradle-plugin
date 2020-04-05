/**
 * Author: Tsiskomichelis Stelios
 * Created On: 21/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: BaristaLoger
 * Role:  Prints Barista log messages when testing
 * Description:
 */
package gr.aueb.android.barista.utilities;

import java.util.List;

public class BaristaLogger {

    /**
     *  Prints a String at the console with the prifix '[BARISTA-PLUGIN]'
     *
     * @param str
     */
    public static void print(String str){
        System.out.println("[BARISTA-PLUGIN] "+str);
    }

    public static void printList(List<String> list) {
        if (list != null) list.forEach(BaristaLogger::print);
    }

}
