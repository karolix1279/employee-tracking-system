package employee.tracking.system.metrics.store;

import employee.tracking.system.metrics.MeterValue;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.List;

    public class GaugeMeterStore<T extends Number> extends AbstractMeterStore<T, Gauge> {

    public GaugeMeterStore(MeterRegistry prometheusMeterRegistry) {
        super(prometheusMeterRegistry);
    }

    @Override
    public Gauge initializeMeter(String meterName, MeterValue<T> meterValue, List<Tag> tags, MeterRegistry meterRegistry) {
        return Gauge.builder(meterName, meterValue, MeterValue::getValue)
                .tags(tags)
                .register(meterRegistry);
    }
}
