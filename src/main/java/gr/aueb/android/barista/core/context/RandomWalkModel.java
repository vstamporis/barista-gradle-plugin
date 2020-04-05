package gr.aueb.android.barista.core.context;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.utilities.Pair;

import java.util.Random;

public class RandomWalkModel extends MovementContextModel {

    private GeoFix geoFix;
    private Double lat, longt;

//    private static double[] movements = new double[]{ -0.000005, 0.000000, 0.000005 };

    private static Pair[][] movements = new Pair[][]{
            {new Pair(0.000005, -0.000005), new Pair(0.000005, 0.000000), new Pair(0.000005, 0.000005)},
            {new Pair(0.000000, -0.000005), new Pair(0.000000, 0.000000), new Pair(0.000000, 0.000005)},
            {new Pair(-0.000005, -0.000005), new Pair(-0.000005, 0.000000), new Pair(-0.000005, 0.000005)},
    };

    public RandomWalkModel(String token, double lat, double longt) {
        super(token);
        this.lat = lat;
        this.longt = longt;
    }

    public RandomWalkModel(String token) {
        super(token);
    }

    private int generateRandomArrayPosition() {
        Random random = new Random(300);

        double r = random.nextInt();

        if (r < 100) {
            return 0;
        }
        else if (r < 200) {
            return 1;
        }
        else if (r < 300) {
            return 2;
        }

        return new Random(3).nextInt();
    }

    @Override
    public Command next(long elapsedTimeMillis) {
        if (this.geoFix == null) {
            if (this.lat == null && this.longt == null) {
                this.lat = this.generateRandomLatitude();
                this.longt = this.generateRandomLongitude();
            }
            this.geoFix = new GeoFix(this.token, this.lat, this.longt);
            return this.geoFix;
        }
        this.lat += movements[generateRandomArrayPosition()][generateRandomArrayPosition()].getLatitude();
        this.longt += movements[generateRandomArrayPosition()][generateRandomArrayPosition()].getLongitude();
        this.geoFix = new GeoFix(this.token, this.lat, this.longt);
        return this.geoFix;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }
}
