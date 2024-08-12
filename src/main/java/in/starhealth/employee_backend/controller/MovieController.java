package in.starhealth.employee_backend.controller;

import in.starhealth.employee_backend.exception.ResourceNotFoundException;
import in.starhealth.employee_backend.model.Employee;
import in.starhealth.employee_backend.repository.EmployeeRepository;
import in.starhealth.employee_backend.service.EmployeeService;
import in.starhealth.employee_backend.service.MovieService;
import in.starhealth.employee_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    public ResponseEntity<Object> getMovies() {
        return movieService.getMovies();
    }

//    @PutMapping("{ID}")
//    public ResponseEntity<Object> updateMovie(@PathVariable long ID) throws IOException {
//        return movieService.updateMovie(ID);
//    }

    @PostMapping()
    public ResponseEntity<Object> createMovie() throws IOException {
        return movieService.createMovie();
    }

}
