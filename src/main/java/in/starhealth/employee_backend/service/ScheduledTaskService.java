package in.starhealth.employee_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ScheduledTaskService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CounterService counterService;

    @Value("${myapp.external.movie_service.url}")
    private String url;

    public String loadPayload(int mid) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:payloads/moviePayload.json");
        try (InputStream inputStream = resource.getInputStream()) {
            String payloadTemplate = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            // Replace mid placeholder with the actual value
            return payloadTemplate.replace("${mid}", String.valueOf(mid));
        }
    }

    @Scheduled(cron = "${myapp.job.frequency}")
    public void sendPostRequest() {
        try {
            // Get the next mid value from CounterService
            int mid = counterService.getNextMid();

            // Load payload with updated mid
            String payload = loadPayload(mid);

            // Create HttpHeaders and set Content-Type to application/json
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            // Create HttpEntity with JSON payload and headers
            HttpEntity<String> requestEntity = new HttpEntity<>(payload, httpHeaders);

            // Make the POST request and get the response
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            System.out.println("Response: " + response.getBody());
        } catch (IOException e) {
            System.err.println("Error loading payload: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during scheduled task: " + e.getMessage());
        }
    }


}
