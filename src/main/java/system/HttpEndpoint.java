package system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface HttpEndpoint {
    ResponseEntity<?> createGetRequest(UriComponentsBuilder uriBuilder, Class model);

    ResponseEntity<?> createPostRequest(UriComponentsBuilder uriBuilder, String requestBody, Class model);
}
