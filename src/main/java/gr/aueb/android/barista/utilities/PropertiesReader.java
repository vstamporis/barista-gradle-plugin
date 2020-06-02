package gr.aueb.android.barista.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class PropertiesReader {

    private String path;

    private Properties properties;

    public PropertiesReader(String path) {
        this.path = path;
    }

    public void loadProperties() {
        try (InputStream input = new FileInputStream(this.path)) {
            properties = new Properties();

            properties.load(input);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getModel(String name) {
        return Boolean.parseBoolean(this.properties.getProperty(name));
    }

    public Integer getMonkeySeed() {
        String temp = this.properties.getProperty("monkey.seed");
        return temp == null ? new Random().nextInt() : Integer.parseInt(temp);
    }

    public Double getWalkLatitude() {
        String temp = this.properties.getProperty("walkModel.latitude");
        return temp == null ? null : Double.parseDouble(temp);
    }

    public Double getWalkLongitude() {
        String temp = this.properties.getProperty("walkModel.longitude");
        return temp == null ? null : Double.parseDouble(temp);
    }
}
