package employee.tracking.system.transformators;

import employee.tracking.system.metrics.store.MeterStore;

public abstract class AbstractDataTransformer implements DataTransformer {

    private MeterStore meterStore;

    public AbstractDataTransformer(MeterStore gaugeMeterStore) {
        this.meterStore = gaugeMeterStore;
    }

}
