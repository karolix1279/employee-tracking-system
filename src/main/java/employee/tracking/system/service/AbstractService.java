package employee.tracking.system.service;

import employee.tracking.system.metrics.store.MeterStore;;

public abstract class AbstractService {

    protected MeterStore meterStore;

    public AbstractService(MeterStore meterStore) {
        this.meterStore = meterStore;
    }
}
