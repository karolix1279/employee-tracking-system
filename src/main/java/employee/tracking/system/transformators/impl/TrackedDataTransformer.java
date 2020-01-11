package employee.tracking.system.transformators.impl;

import employee.tracking.system.metrics.store.GaugeMeterStore;
import employee.tracking.system.metrics.store.MeterStore;
import employee.tracking.system.model.dto.TrackedInformationDto;
import employee.tracking.system.transformators.AbstractDataTransformer;
import io.micrometer.core.instrument.Tag;

import java.util.Arrays;
import java.util.List;

public class TrackedDataTransformer extends AbstractDataTransformer {

    public TrackedDataTransformer(MeterStore meterStore) {
        super(meterStore);
    }

    @Override
    public void expose(TrackedInformationDto trackedInformationDto) {

        List<Tag> tagList = Arrays.asList(
                Tag.of("computerName",trackedInformationDto.getComputerName())
        );

        super.meterStore.updateMeterValue("clicked_buttons_mouse",
                trackedInformationDto.getCountClickedButtonsMouse(), tagList);

        super.meterStore.updateMeterValue("clicked_buttons_keyboard",
                trackedInformationDto.getCountClickedButtonsMouse(), tagList);
    }
}
