package employee.tracking.system.service;

import employee.tracking.system.expositor.DataExpositor;
import employee.tracking.system.metrics.store.MeterStore;

public abstract class AbstractMetricExposition implements MetricExposition {

    protected MeterStore meterStore;
    protected DataExpositor dataExpositor;

    public AbstractMetricExposition(MeterStore meterStore) {
        this.meterStore = meterStore;
    }

    @Override
    public void exposeMetric(DataExpositor dataExpositor) {
        dataExpositor.expose();
    }
}
