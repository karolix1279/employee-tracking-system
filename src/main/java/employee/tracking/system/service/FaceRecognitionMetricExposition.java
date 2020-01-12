package employee.tracking.system.service;

import employee.tracking.system.endpoint.external.FaceRecognitionEndpoint;
import employee.tracking.system.metrics.store.MeterStore;import employee.tracking.system.transformators.impl.FaceRecognitionTransformer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FaceRecognitionMetricExposition extends AbstractMetricExposition {

    public FaceRecognitionMetricExposition(MeterStore meterStore) {
        super(meterStore);
    }

    public void exposeMetric(FaceRecognitionEndpoint faceRecognitionEndpoint,
                             MultipartFile file,
                             String computerName) {
        super.exposeMetric(dataTransformer =
                new FaceRecognitionTransformer(super.meterStore, faceRecognitionEndpoint, file, computerName));
    }
}
