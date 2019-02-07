package gr.aueb.android.barista;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.DimensionUnit;
import gr.aueb.android.barista.core.model.WmSize;

public class JsonDataHelper {

    public static final String GEOFIX_JSON = "{\"type\":\"GeoFix\",\"sessionToken\":\"1\",\"latitude\":1.0,\"longitude\":2.0}";
    public static final String WM_SIZE_JSON = "{\"type\":\"WmSize\",\"sessionToken\":\"2\",\"width\":1280,\"height\":800,\"reset\":false,\"unit\":\"DPI\"}";
    public static final String WM_SIZE_JSON_2 ="{\"type\":\"WmSize\",\"sessionToken\":\"8ba3eee6-51e6-4d8a-8e5f-1243746ad475\",\"height\":600,\"reset\":false,\"unit\":\"DPI\",\"width\":500}";
    public static final String DENSITY_JSON = "{\"type\":\"WmDensity\",\"sessionToken\":\"3\",\"density\":600}";

    public static final Command WM_SIZE_COMMAND = new WmSize("1",500,600,false, DimensionUnit.PIXEL);



}
