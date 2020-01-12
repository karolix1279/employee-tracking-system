package employee.tracking.system.service;

import employee.tracking.system.metrics.store.MeterStore;
import employee.tracking.system.model.dto.request.TrackedInformationDto;
import employee.tracking.system.transformators.impl.TrackedDataTransformer;
import org.springframework.stereotype.Service;

@Service
public class TrackedDataMetricExposition extends AbstractMetricExposition {

    public TrackedDataMetricExposition(MeterStore meterStore) {
        super(meterStore);
    }

    public void exposeMetric(TrackedInformationDto trackedInformationDto) {
         super.exposeMetric(dataTransformer
                 = new TrackedDataTransformer(super.meterStore, trackedInformationDto));
    }
}
