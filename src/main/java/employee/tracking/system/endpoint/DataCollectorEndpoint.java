package employee.tracking.system.endpoint;

import employee.tracking.system.model.dto.TrackedInformationDto;
import employee.tracking.system.service.TrackedDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@Slf4j
public class DataCollectorEndpoint {

    private TrackedDataService trackedDataService;

    public DataCollectorEndpoint(TrackedDataService trackedDataService) {
        this.trackedDataService = trackedDataService;
    }

    @PostMapping("collect")
    public void collectData1(TrackedInformationDto trackedInformationDto) {
        log.info("Request received");
        trackedDataService.exposeMetric(trackedInformationDto);
    }
}
