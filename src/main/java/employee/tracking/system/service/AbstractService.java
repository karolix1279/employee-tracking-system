package employee.tracking.system.service;

import io.micrometer.core.instrument.MeterRegistry;

public abstract class AbstractService {

    protected MeterRegistry meterRegistry;

    public AbstractService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
}
