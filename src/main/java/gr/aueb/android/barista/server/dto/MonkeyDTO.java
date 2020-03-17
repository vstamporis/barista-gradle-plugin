package gr.aueb.android.barista.server.dto;

import gr.aueb.android.barista.core.model.RemoteMonkey;
import gr.aueb.android.barista.server.mapper.CommandMapper;
import gr.aueb.android.barista.server.dto.CommandDTO;

public class MonkeyDTO extends CommandDTO<RemoteMonkey> {

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
    public RemoteMonkey toDomainObject() {
        return CommandMapper.INSTANCE.fromMonkeyDTO(this);
    }
}
