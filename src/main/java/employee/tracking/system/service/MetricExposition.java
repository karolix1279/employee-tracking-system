package employee.tracking.system.service;

import employee.tracking.system.transformators.DataTransformer;

public interface MetricExposition {

    void exposeMetric(DataTransformer dataTransformer);
}
