package gr.aueb.android.barista.rest.dto;

import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.GpsState;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

public class GpsStatusDTO extends CommandDTO<GpsState> {

    private boolean enabled;

    public GpsStatusDTO() {

    }

    public GpsStatusDTO(String sessionToken, boolean enabled) {
        super(sessionToken);
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public GpsState toDomainObject() {
        return CommandMapper.INSTANCE.fromGPSDDTO(this);
    }

}
