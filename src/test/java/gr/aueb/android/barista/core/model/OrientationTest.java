package gr.aueb.android.barista.core.model;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class OrientationTest {

    @Test
    public void testParseReults(){
        GetOrientation orientationCommand = new GetOrientation(null);
        String testString = "Row: 0 name=user_rotation, value=1";


        new BufferedReader(new StringReader(testString))
                .lines().forEach(line->orientationCommand.parseResult(Stream.of(line)));

        assertEquals(1, orientationCommand.getOrientationValue());
    }

}
