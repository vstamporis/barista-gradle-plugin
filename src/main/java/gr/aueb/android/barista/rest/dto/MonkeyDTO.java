package gr.aueb.android.barista.rest.dto;

import gr.aueb.android.barista.core.fuzzer.MonkeyEventGenerator;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

public class MonkeyDTO extends CommandDTO<MonkeyEventGenerator> {

    private int seed, count, throttle;
    private String apk;

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

    @Override
    public MonkeyEventGenerator toDomainObject() {
        return CommandMapper.INSTANCE.fromMonkeyDTO(this);
    }
}
