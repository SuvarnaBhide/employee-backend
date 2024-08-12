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

    @Autowired
    private CounterService counterService;

    @Value("${myapp.external.movie_service.url}")
    String url;

    public String loadPayload(int mid) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:payloads/moviePayload.json");
        String payloadTemplate = new String(Files.readAllBytes(Paths.get(resource.getURI())));

        // Replace mid placeholder with the actual value
        return payloadTemplate.replace("${mid}", String.valueOf(mid));
    }

    public ResponseEntity<Object> getMovies() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class
        );
    }

//    public ResponseEntity<Object> updateMovie(long id) throws IOException {
//        String urlWithId = String.format("%s/%d", url, id); // Format the URL with the ID
//
//        // Create the JSON payload as a String
//        String payload = loadPayload();
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(payload, httpHeaders);
//
//        return restTemplate.exchange(
//                urlWithId,
//                HttpMethod.PUT,
//                requestEntity,
//                Object.class
//        );
//    }

    public ResponseEntity<Object> createMovie() throws IOException {

        // Get the next mid value from CounterService
        int mid = counterService.getNextMid();

        // Load payload with updated mid
        String payload = loadPayload(mid);

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

