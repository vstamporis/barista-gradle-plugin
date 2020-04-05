/**
 * Author: Tsiskomichelis Stelios
 * Created On: 21/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: TestMonitor
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.emulator.helpers;

public class TestMonitor {

    private static int totalTests;

    public static void setRunningTests(int numOfTests){
        totalTests = numOfTests;
    }

    public static int getRuningTests(){
        return totalTests;
    }

    public static void testFinished(){
        totalTests --;
    }

    public static boolean hasActiveTests(){
        if(totalTests >0 )
            return true;
        else
            return false;
    }


}
