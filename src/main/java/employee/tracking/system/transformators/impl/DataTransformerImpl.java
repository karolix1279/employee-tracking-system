package employee.tracking.system.transformators.impl;

import employee.tracking.system.metrics.store.GaugeMeterStore;
import employee.tracking.system.metrics.store.MeterStore;
import employee.tracking.system.model.dto.TrackedInformationDto;
import employee.tracking.system.transformators.AbstractDataTransformer;

public class DataTransformerImpl extends AbstractDataTransformer {

    private GaugeMeterStore gaugeMeterStore;

    public DataTransformerImpl(GaugeMeterStore gaugeMeterStore) {
        super(gaugeMeterStore);
    }

    @Override
    public void transform(TrackedInformationDto trackedInformationDto, MeterStore meterStore) {

    }
}
