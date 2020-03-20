package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.fuzzer.MonkeyEventGenerator;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

@JsonTypeName("Monkey")
public class MonkeyDTO extends CommandDTO<MonkeyEventGenerator> {

    private int seed, count, throttle;
    private String apk;

    public MonkeyDTO() {

    }

    public MonkeyDTO(String sessionToken, int seed, int count, int throttle, String apk) {
        super(sessionToken);
        this.seed = seed;
        this.count = count;
        this.throttle = throttle;
        this.apk = apk;
    }

    public boolean getResult() {
        return true;
    }

    public void execute(int seed, int count, int throttle, String apk) {
        this.seed = seed;
        this.count = count;
        this.throttle = throttle;
        this.apk = apk;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getThrottle() {
        return throttle;
    }

    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    @Override
    public MonkeyEventGenerator toDomainObject() {
        return CommandMapper.INSTANCE.fromMonkeyDTO(this);
    }
}
