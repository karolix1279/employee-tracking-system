package employee.tracking.system.endpoint;

import employee.tracking.system.model.dto.TrackedInformationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class DataCollectorEndpoint {

    @PostMapping("collect")
    public String collectData() {

        return "dupa";
    }

    @PostMapping("collect1")
    public String collectData1(TrackedInformationDto trackedInformationDto) {
        return "dupa";
    }
}
