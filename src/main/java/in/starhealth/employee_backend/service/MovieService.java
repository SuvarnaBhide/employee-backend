package in.starhealth.employee_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${myapp.external.movie_service.url}")
    String url;

    public String loadPayload() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:payloads/moviePayload.json");
        return new String(Files.readAllBytes(Paths.get(resource.getURI())));
    }

    public ResponseEntity<Object> getMovies() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class
        );
    }

    public ResponseEntity<Object> createMovie() throws IOException {

        // Create the JSON payload as a String
        String payload = loadPayload();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(payload, httpHeaders);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
    }

}

