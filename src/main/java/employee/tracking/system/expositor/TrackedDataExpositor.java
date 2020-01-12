package employee.tracking.system.expositor;

import employee.tracking.system.metrics.store.MeterStore;
import employee.tracking.system.model.dto.request.TrackedInformationDto;
import io.micrometer.core.instrument.Tag;

import java.util.Collections;
import java.util.List;

public class TrackedDataExpositor extends AbstractDataExpositor {

    private TrackedInformationDto trackedInformationDto;

    public TrackedDataExpositor(MeterStore meterStore, TrackedInformationDto trackedInformationDto) {
        super(meterStore);
        this.trackedInformationDto = trackedInformationDto;
    }

    @Override
    public void expose() {

        String name = trackedInformationDto.getComputerName()
                + "-"
                + trackedInformationDto.getCurrentUser();

        String meterName = "tracked_data";


        List<Tag> tagList = Collections.singletonList(
                Tag.of("name", name)
        );

        super.meterStore.updateMeterValue(meterName + "_mouse",
                trackedInformationDto.getCountClickedButtonsMouse(), tagList);

        super.meterStore.updateMeterValue(meterName + "_keyboard",
                trackedInformationDto.getCountClickedButtonsKeyboard(), tagList);

        super.meterStore.updateMeterValue(meterName + "_cpu",
                trackedInformationDto.getCpuUsage(), tagList);

        super.meterStore.updateMeterValue(meterName + "_memory",
                trackedInformationDto.getMemoryUsage(), tagList);

        super.meterStore.updateMeterValue(meterName + "_screen_changes",
                trackedInformationDto.getScreenChanges(), tagList);

        super.meterStore.updateMeterValue(meterName + "_devices_changes",
                trackedInformationDto.getConnectedDevicesChanges(), tagList);
    }
}
