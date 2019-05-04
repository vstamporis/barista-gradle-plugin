/**
 * Author: Tsiskomichelis Stelios
 * Created On: 2/5/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmGetDensity
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class WmGetDensity extends AbstractAdbCommand {
    private int density;

    public WmGetDensity(String sessionToken){
        super(sessionToken);

    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.WM_DENSITY;
    }

    @Override
    public void parseResult(Stream<String> resultLines){
        BaristaLogger.print("Using custom output parser for fetching density.");
        resultLines.filter(new Predicate<String>() {
            @Override
            public boolean test(String line) {
                if (line.matches("Override density:(.*)")) {
                    return true;
                }
                return false;
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String line) {
                String densityString = line.split(":")[1].trim();
                density = Integer.parseInt(densityString);
            }
        });


    }


    public int getDensity() {
        return this.density;
    }
}
