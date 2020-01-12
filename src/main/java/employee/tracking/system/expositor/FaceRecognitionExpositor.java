package employee.tracking.system.expositor;

import employee.tracking.system.endpoint.external.FaceRecognitionEndpoint;
import employee.tracking.system.metrics.store.MeterStore;
import io.micrometer.core.instrument.Tag;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FaceRecognitionExpositor extends AbstractDataExpositor {

    private FaceRecognitionEndpoint faceRecognitionEndpoint;
    private MultipartFile file;
    private String name;

    public FaceRecognitionExpositor(MeterStore meterStore,
                                    FaceRecognitionEndpoint faceRecognitionEndpoint,
                                    MultipartFile file,
                                    String computerNameDto) {
        super(meterStore);
        this.faceRecognitionEndpoint = faceRecognitionEndpoint;
        this.file = file;
        this.name = computerNameDto;
    }

    @Override
    public void expose() {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = faceRecognitionEndpoint.createPostRequest(UriComponentsBuilder.fromUriString("/face/v1.0/detect"),
                    new HttpEntity(file.getBytes()),
                    String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String response = (String) responseEntity.getBody();

        List<Tag> tagList = new LinkedList<>();
        tagList.add(Tag.of("name", name));

        System.out.println(responseEntity.getBody());

        if((response.contains("faceId"))) {
            super.meterStore.updateMeterValue("human_face",
                    1, tagList);
        } else {
            super.meterStore.updateMeterValue("human_face",
                    0, tagList);
        }


    }
}
