package employee.tracking.system.expositor;

import employee.tracking.system.metrics.store.MeterStore;

public abstract class AbstractDataExpositor implements DataExpositor {

    protected MeterStore meterStore;

    public AbstractDataExpositor(MeterStore meterStore) {
        this.meterStore = meterStore;
    }

}
