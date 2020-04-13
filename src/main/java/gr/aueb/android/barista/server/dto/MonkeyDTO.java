package gr.aueb.android.barista.server.dto;

import gr.aueb.android.barista.core.model.RemoteMonkey;
import gr.aueb.android.barista.server.mapper.CommandMapper;
import gr.aueb.android.barista.server.dto.CommandDTO;

public class MonkeyDTO extends CommandDTO<RemoteMonkey> {

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

    @Override
    public RemoteMonkey toDomainObject() {
        return CommandMapper.INSTANCE.fromMonkeyDTO(this);
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
}
