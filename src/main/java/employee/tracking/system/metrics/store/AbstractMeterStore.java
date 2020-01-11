package employee.tracking.system.metrics.store;

import employee.tracking.system.metrics.MeterValue;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMeterStore<T extends Number, M extends Meter> implements MeterStore<T, M> {

    private Map<String, MeterValue<T>> meterValues;
    private Map<String, M> meters;

    private MeterRegistry meterRegistry;

    public AbstractMeterStore(MeterRegistry meterRegistry) {
        this.meterValues = new HashMap<>();
        this.meters = new HashMap<>();
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void updateMeterValue(String meterName, T value, List<Tag> tags) {
        String meterHash = MeterValue.getHash(meterName, tags);

        if (!meterValues.containsKey(meterHash)) {
            MeterValue<T> gaugeValue = new MeterValue<>(meterName, tags);

            gaugeValue.setValue(value);
            meters.put(meterHash, initializeMeter(meterName, gaugeValue, tags, meterRegistry));
            meterValues.put(meterHash, gaugeValue);
        }

        meterValues.get(meterHash).setValue(value);
    }

    public Map<String, MeterValue<T>> getMeterValues() {
        return meterValues;
    }

    public Map<String, M> getMeters() {
        return meters;
    }
}
