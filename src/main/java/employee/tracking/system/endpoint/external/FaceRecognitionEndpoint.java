package employee.tracking.system.endpoint.external;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class FaceRecognitionEndpoint extends RestTemplateSystemEndpoint {

    public ResponseEntity<?> createPostRequest(UriComponentsBuilder uriBuilder, HttpEntity entity, Class model) {
        return createRequest(uriBuilder, HttpMethod.POST, entity, model);
    }
}