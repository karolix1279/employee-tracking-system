package employee.tracking.system.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrackedInformationDto {

    private String computerName;

    private int countClickedButtonsKeyboard;
    private int countClickedButtonsMouse;

}
