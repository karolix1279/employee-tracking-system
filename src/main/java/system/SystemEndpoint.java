package system;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public interface SystemEndpoint extends HttpEndpoint {
    RestTemplate initializeRestTemplate(RestTemplateBuilder restTemplateBuilder);

    ResponseEntity<?> createRequest(UriComponentsBuilder uriBuilder, HttpMethod method, HttpEntity httpEntity, Class model);
}
