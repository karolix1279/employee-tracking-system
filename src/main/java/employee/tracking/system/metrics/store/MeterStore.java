package employee.tracking.system.metrics.store;

import employee.tracking.system.metrics.MeterValue;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.List;
import java.util.Map;

public interface MeterStore<T extends Number, M extends Meter> {

    void updateMeterValue(String gaugeName, T value, List<Tag> tags);

    M initializeMeter(String meterName, MeterValue<T> value, List<Tag> tags, MeterRegistry meterRegistry);

    Map<String, MeterValue<T>> getMeterValues();
}
