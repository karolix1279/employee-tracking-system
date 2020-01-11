package employee.tracking.system.endpoint;

import employee.tracking.system.config.swagger.SwaggerDocumentation;
import employee.tracking.system.model.dto.TrackedInformationDto;
import employee.tracking.system.service.TrackedDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@Slf4j
@SwaggerDocumentation
public class DataCollectorEndpoint {

    private TrackedDataService trackedDataService;

    public DataCollectorEndpoint(TrackedDataService trackedDataService) {
        this.trackedDataService = trackedDataService;
    }

    @PostMapping(value = "collect")
    public void collectData1(@RequestBody TrackedInformationDto trackedInformationDto) {
        log.info("Request received {}", trackedInformationDto.toString());
        trackedDataService.exposeMetric(trackedInformationDto);
    }
}
