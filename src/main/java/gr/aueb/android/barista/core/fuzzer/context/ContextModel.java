package gr.aueb.android.barista.core.fuzzer.context;

import java.util.Random;

public abstract class ContextModel {

    protected boolean stop;
    protected String token;
    protected Random random;

    public ContextModel(String token) {
        this.stop = false;
        this.token = token;
        this.random = new Random();
    }

    public abstract void execute();

    public int generateRandomInt(int min, int max) {

        int number = this.random.nextInt((max - min) + 1) + min;

        return number;
    }

    public void stop() {
        this.stop = true;
    }

    public String getToken() {
        return this.token;
    }
}
