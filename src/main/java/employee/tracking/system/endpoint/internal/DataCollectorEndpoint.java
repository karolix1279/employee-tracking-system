package employee.tracking.system.endpoint.internal;

import employee.tracking.system.config.swagger.SwaggerDocumentation;
import employee.tracking.system.model.dto.request.TrackedInformationDto;
import employee.tracking.system.service.TrackedDataMetricExposition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@Slf4j
@SwaggerDocumentation
public class DataCollectorEndpoint {

    private TrackedDataMetricExposition trackedDataMetricExposition;

    public DataCollectorEndpoint(TrackedDataMetricExposition trackedDataMetricExposition) {
        this.trackedDataMetricExposition = trackedDataMetricExposition;
    }

    @PostMapping(value = "collect")
    public void collectData(@RequestBody TrackedInformationDto trackedInformationDto) {
        log.info("Request received {}", trackedInformationDto.toString());
        trackedDataMetricExposition.exposeMetric(trackedInformationDto);
    }
}
