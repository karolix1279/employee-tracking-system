package employee.tracking.system.endpoint.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

public abstract class RestTemplateSystemEndpoint implements SystemEndpoint {

    @Value("${face.recognition.azure.url}")
    private String url;

    private RestTemplate restTemplate;

    @PostConstruct
    private void setup() {
        this.restTemplate =  new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader("Content-Type","application/octet-stream")
                .defaultHeader("Ocp-Apim-Subscription-Key","cdea292b1e01491c90f280e9029d75d5")
                .build();
    }

    @Override
    public ResponseEntity<?> createRequest(UriComponentsBuilder uriBuilder, HttpMethod method, HttpEntity httpEntity, Class model) {
        return this.restTemplate.exchange(
                uriBuilder.build(true).toUriString(),
                method,
                httpEntity,
                model
        );
    }
}
