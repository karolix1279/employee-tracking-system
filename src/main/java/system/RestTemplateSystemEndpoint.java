package system;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

public abstract class RestTemplateSystemEndpoint implements SystemEndpoint {

    private RestTemplate restTemplate;

    private String serviceName;


    public RestTemplateSystemEndpoint(String serviceName) {
        this.serviceName = serviceName;
    }

    @PostConstruct
    private void setup() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = initializeRestTemplate(restTemplateBuilder);
    }

    @Override
    public ResponseEntity<?> createGetRequest(UriComponentsBuilder uriBuilder, Class model) {
        return createRequest(uriBuilder, HttpMethod.GET, null, model);
    }

    @Override
    public ResponseEntity<?> createPostRequest(UriComponentsBuilder uriBuilder, String requestBody, Class model) {
        return createRequest(uriBuilder, HttpMethod.POST, new HttpEntity<>(requestBody), model);
    }

    @Override
    public ResponseEntity<?> createRequest(UriComponentsBuilder uriBuilder, HttpMethod method, HttpEntity httpEntity, Class model) {
        return this.restTemplate.exchange(
                uriBuilder.build(false).toUriString(),
                method,
                httpEntity,
                model
        );
    }
}
