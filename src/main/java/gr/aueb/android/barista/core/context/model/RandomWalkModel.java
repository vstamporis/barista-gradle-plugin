package gr.aueb.android.barista.core.context.model;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.GeoFix;
import gr.aueb.android.barista.utilities.Pair;

import java.util.Random;

public class RandomWalkModel extends MovementContextModel {

    private GeoFix geoFix;
    private double lat, longt;

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

    /*@Override
    public void execute() {
        new Thread(() -> {
            CommandExecutorImpl executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
            double lat = generateRandomLatitude();
            double longt = generateRandomLongitude();
            double lat = 37.975374;
            double longt = 23.734873;
            this.geoFix = new GeoFix(this.token, lat, longt);
            executor.executeCommand(this.geoFix);
            BaristaLogger.print("Initializing random walk model with pos: " + lat + " " + longt);
            while (!this.stop) {
                lat += movements[generateRandomArrayPosition()][generateRandomArrayPosition()].getLatitude();
                longt += movements[generateRandomArrayPosition()][generateRandomArrayPosition()].getLongitude();
                this.geoFix = new GeoFix(this.token, lat, longt);
                executor.executeCommand(this.geoFix);
                BaristaLogger.print("Moving to pos: " + lat + " " + longt);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/

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
        this.lat += movements[generateRandomArrayPosition()][generateRandomArrayPosition()].getLatitude();
        this.longt += movements[generateRandomArrayPosition()][generateRandomArrayPosition()].getLongitude();
        return new GeoFix(this.token, this.lat, this.longt);
    }
}
