package employee.tracking.system.service;

import employee.tracking.system.endpoint.external.FaceRecognitionEndpoint;
import employee.tracking.system.expositor.FaceRecognitionExpositor;
import employee.tracking.system.metrics.store.MeterStore;
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
        super.exposeMetric(dataExpositor =
                new FaceRecognitionExpositor(super.meterStore, faceRecognitionEndpoint, file, computerName));
    }
}
