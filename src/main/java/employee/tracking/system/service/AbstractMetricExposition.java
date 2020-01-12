package employee.tracking.system.service;

import employee.tracking.system.metrics.store.MeterStore;
import employee.tracking.system.transformators.DataTransformer;

public abstract class AbstractMetricExposition implements MetricExposition {

    protected MeterStore meterStore;
    protected DataTransformer dataTransformer;

    public AbstractMetricExposition(MeterStore meterStore) {
        this.meterStore = meterStore;
    }

    @Override
    public void exposeMetric(DataTransformer dataTransformer) {
        dataTransformer.expose();
    }
}
