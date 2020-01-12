package employee.tracking.system.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrackedInformationDto {

    private String computerName;

    private int countClickedButtonsKeyboard;
    private int countClickedButtonsMouse;

    private String cpu_usage;
    private String memoryUsage;

    private String ip;
    private String currentUser;

    private int screenChanges;
    private int connectedDevicesChanges;
}
