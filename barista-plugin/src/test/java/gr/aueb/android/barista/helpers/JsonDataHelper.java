package gr.aueb.android.barista.helpers;


public class JsonDataHelper {

    public static final String GEOFIX_JSON = "{\"type\":\"GeoFix\",\"sessionToken\":\"1\",\"latitude\":1.0,\"longitude\":2.0}";
    public static final String WM_SIZE_JSON = "{\"type\":\"WmSize\",\"sessionToken\":\"2\",\"width\":1280,\"height\":800,\"reset\":false,\"unit\":\"DPI\"}";
    public static final String WM_SIZE_JSON_2 ="{\"type\":\"WmSize\",\"sessionToken\":\"8ba3eee6-51e6-4d8a-8e5f-1243746ad475\",\"height\":600,\"reset\":false,\"unit\":\"DPI\",\"width\":500}";
    public static final String DENSITY_JSON = "{\"type\":\"WmDensity\",\"sessionToken\":\"3\",\"density\":600}";
    public static final String RESET_JSON = "{\"type\":\"WmSizeReset\",\"sessionToken\":\"1\"}";
    public static final String BATTERY_LVL_JSON = "{\"type\":\"BatteryLevel\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"level\":"+ConstantValues.level+"}";
    public static final String GRANT_JSON = "{\"type\":\"PmGrant\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"permission\":\""+ConstantValues.permission+"\"}";
    public static final String BATTERY_CHARGE_JSON = "{\"type\":\"BatteryCharge\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"plugged\":"+ConstantValues.isPlugged+"}";
    public static final String WIFI_JSON = "{\"type\":\"SvcWifi\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"enabled\":"+ConstantValues.wifiEnable+"}";
    public static final String DATA_JSON = "{\"type\":\"SvcData\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"enabled\":"+ConstantValues.dataEnable+"}";

}
