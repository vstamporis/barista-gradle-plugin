package gr.aueb.android.barista.server.dto;


import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.model.SetOrientation;
import gr.aueb.android.barista.server.mapper.CommandMapper;

@JsonTypeName("SetOrientation")
public class SetOrientationDTO extends CommandDTO<SetOrientation> {

    private int orientation;

    public SetOrientationDTO(){}

    public SetOrientationDTO(String sessionToken, int orientation){
        super(sessionToken);
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public SetOrientation toDomainObject() {
        return CommandMapper.INSTANCE.fromSetOrientationDTO(this);
    }
}
