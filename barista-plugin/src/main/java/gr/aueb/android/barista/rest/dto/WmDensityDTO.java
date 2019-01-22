package gr.aueb.android.barista.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("WmDensity")
public class WmDensityDTO extends Command {

    private int density;

    public WmDensityDTO() {
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }
}
