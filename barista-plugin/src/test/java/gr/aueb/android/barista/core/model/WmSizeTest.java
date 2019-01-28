package gr.aueb.android.barista.core.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WmSizeTest {


    @Test
    public void getCommandString() {
        String expectedCommand = "shell wm size 600x400";
        WmSize resizeCommand = new WmSize("1", 600, 400, false, DimensionUnit.PIXEL);
        assertEquals(resizeCommand.getCommandString(), expectedCommand);
    }
}