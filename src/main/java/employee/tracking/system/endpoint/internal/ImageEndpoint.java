package employee.tracking.system.endpoint.internal;

import employee.tracking.system.config.swagger.SwaggerDocumentation;
import employee.tracking.system.endpoint.external.FaceRecognitionEndpoint;
import employee.tracking.system.service.FaceRecognitionMetricExposition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/")
@Slf4j
@SwaggerDocumentation
public class ImageEndpoint {

    private FaceRecognitionMetricExposition faceRecognitionMetricExposition;
    private FaceRecognitionEndpoint faceRecognitionEndpoint;

    public ImageEndpoint(FaceRecognitionMetricExposition faceRecognitionMetricExposition,
                         FaceRecognitionEndpoint faceRecognitionEndpoint) {
        this.faceRecognitionMetricExposition = faceRecognitionMetricExposition;
        this.faceRecognitionEndpoint = faceRecognitionEndpoint;
    }

    @PostMapping(value = "image/{computerName}")
    public void getImage(@RequestParam("file") MultipartFile file, @PathVariable String computerName) {
        log.info("Request received {}", computerName);
        faceRecognitionMetricExposition.exposeMetric(faceRecognitionEndpoint, file, computerName);

    }
}
