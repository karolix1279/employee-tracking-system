package employee.tracking.system.service;

import employee.tracking.system.metrics.store.GaugeMeterStore;
import employee.tracking.system.model.dto.TrackedInformationDto;
import employee.tracking.system.transformators.DataTransformer;
import employee.tracking.system.transformators.impl.TrackedDataTransformer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class TrackedDataService extends AbstractService {

    private DataTransformer dataTransformer;

    public TrackedDataService(MeterRegistry meterRegistry) {
        super(meterRegistry);
    }

    public void exposeMetric(TrackedInformationDto trackedInformationDto) {
         dataTransformer = new TrackedDataTransformer(new GaugeMeterStore(super.meterRegistry));

         dataTransformer.expose(trackedInformationDto);
    }
}
