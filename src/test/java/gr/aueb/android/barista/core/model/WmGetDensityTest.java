package gr.aueb.android.barista.core.model;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class WmGetDensityTest {

    @Test
    public void testParseResults(){
      WmGetDensity wmGetDensity = new WmGetDensity(null);
      String testInput = "Physical density: 420\n" +
              "Override density: 200\n";

        new BufferedReader(new StringReader(testInput))
                .lines().forEach(line->wmGetDensity.parseResult(Stream.of(line)));

        assertEquals(200, wmGetDensity.getDensity());

    }


}