package employee.tracking.system.transformators.impl;

import employee.tracking.system.metrics.store.MeterStore;
import employee.tracking.system.model.dto.request.TrackedInformationDto;
import employee.tracking.system.transformators.AbstractDataTransformer;
import io.micrometer.core.instrument.Tag;

import java.util.Collections;
import java.util.List;

public class TrackedDataTransformer extends AbstractDataTransformer {

    private TrackedInformationDto trackedInformationDto;

    public TrackedDataTransformer(MeterStore meterStore, TrackedInformationDto trackedInformationDto) {
        super(meterStore);
        this.trackedInformationDto = trackedInformationDto;
    }

    @Override
    public void expose() {

        String name = trackedInformationDto.getCountClickedButtonsMouse()
                + "-"
                + trackedInformationDto.getCurrentUser();

        String meterName = "tracked_data";


        List<Tag> tagList = Collections.singletonList(
                Tag.of("name", name)
        );


        super.meterStore.updateMeterValue(meterName,
                trackedInformationDto.getCountClickedButtonsMouse(), tagList);

        super.meterStore.updateMeterValue(meterName,
                trackedInformationDto.getCountClickedButtonsMouse(), tagList);

        super.meterStore.updateMeterValue(meterName,
                Double.valueOf(trackedInformationDto.getCpu_usage()), tagList);

        super.meterStore.updateMeterValue(meterName,
                Double.valueOf(trackedInformationDto.getMemoryUsage()), tagList);

        super.meterStore.updateMeterValue(meterName,
                trackedInformationDto.getScreenChanges(), tagList);

        super.meterStore.updateMeterValue(meterName,
                trackedInformationDto.getConnectedDevicesChanges(), tagList);
    }
}
