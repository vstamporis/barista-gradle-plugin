/**
 * Author: Tsiskomichelis Stelios
 * Created On: 2/12/2018
 * Project: barista-plugin
 * <p>
 * ClassName: StreamGobler
 * Role:
 * Description:
 */
package gr.aueb.android.barista.utils;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 *  from : https://www.baeldung.com/run-shell-command-in-java
 */
public class StreamGobbler implements Runnable{

    private InputStream inputStream;
    private Consumer<String> consumer;

    public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
        this.inputStream = inputStream;
        this.consumer = consumer;
    }

    @Override
    public void run() {

    }
}
