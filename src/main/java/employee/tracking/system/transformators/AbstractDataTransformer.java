package employee.tracking.system.transformators;

import employee.tracking.system.metrics.store.MeterStore;

public abstract class AbstractDataTransformer implements DataTransformer {

    protected MeterStore meterStore;

    public AbstractDataTransformer(MeterStore meterStore) {
        this.meterStore = meterStore;
    }

}
