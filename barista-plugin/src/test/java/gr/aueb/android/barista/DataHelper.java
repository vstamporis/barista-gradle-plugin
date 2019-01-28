package gr.aueb.android.barista;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.DimensionUnit;
import gr.aueb.android.barista.core.model.WmSize;

public class DataHelper {

    public static final String GEOFIX_JSON = "{\"type\":\"GeoFix\",\"sessionToken\":\"1\",\"latitude\":1.0,\"longitude\":2.0}";
    public static final String WM_SIZE_JSON = "{\"type\":\"WmSize\",\"sessionToken\":\"2\",\"width\":1280,\"height\":800,\"reset\":false,\"unit\":\"DPI\"}";
    public static final String DENSITY_JSON = "{\"type\":\"WmDensity\",\"sessionToken\":\"3\",\"density\":600}";

    public static final Command WM_SIZE_COMMAND = new WmSize("1",500,600,false, DimensionUnit.PIXEL);



}
