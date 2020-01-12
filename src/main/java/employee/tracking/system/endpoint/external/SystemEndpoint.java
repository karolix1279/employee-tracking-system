package employee.tracking.system.endpoint.external;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface SystemEndpoint {
    ResponseEntity<?> createRequest(UriComponentsBuilder uriBuilder, HttpMethod method, HttpEntity httpEntity, Class model);
}
