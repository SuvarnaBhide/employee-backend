package in.starhealth.employee_backend.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CounterService {

    private final AtomicInteger midCounter = new AtomicInteger(100);

    public int getNextMid() {
        return midCounter.getAndIncrement();
    }
}

