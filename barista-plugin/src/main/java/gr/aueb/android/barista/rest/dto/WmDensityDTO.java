package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.WmDensity;
import gr.aueb.android.barista.rest.mapper.CommandMapper;

@JsonTypeName("WmDensity")
public class WmDensityDTO extends CommandDTO<WmDensity> {

    private int density;

    public WmDensityDTO() {
    }

    public WmDensityDTO(String sessionToken, int density) {
        super(sessionToken);
        this.density = density;
    }

    @Override
    public WmDensity toDomainObject() {
        return CommandMapper.INSTANCE.fromWmDensityDTO(this);

    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }
}
