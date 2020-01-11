package employee.tracking.system.transformators;

import employee.tracking.system.metrics.store.MeterStore;
import employee.tracking.system.model.dto.TrackedInformationDto;

public interface DataTransformer {

    void expose(TrackedInformationDto trackedInformationDto);
}
