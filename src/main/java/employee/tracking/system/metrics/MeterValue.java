package employee.tracking.system.metrics;

import io.micrometer.core.instrument.Tag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MeterValue<T extends Number> {
    private String meterName;
    private Set<Tag> tags;
    private T value;

    public MeterValue(String meterName, List<Tag> tags) {
        this.meterName = meterName;
        this.tags = new HashSet<>();

        this.tags.addAll(tags);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getMeterName() {
        return meterName;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Double getValue() {
        return value.doubleValue();
    }

    public static String getHash(String name, List<Tag> tags) {
        StringBuilder hashBuilder = new StringBuilder();
        hashBuilder.append(name);
        hashBuilder.append("{");
        hashBuilder.append(tags.stream()
                .reduce("", (String partial, Tag currentTag) -> partial + currentTag.getKey() + "=" + currentTag.getValue() + ",", String::concat));
        hashBuilder.append("}");

        return hashBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MeterValue)) {
            return super.equals(obj);
        }

        MeterValue<?> otherValue = (MeterValue<?>) obj;

        return otherValue.getMeterName().equals(this.meterName)
                && otherValue.getTags().equals(this.tags);
    }
}
