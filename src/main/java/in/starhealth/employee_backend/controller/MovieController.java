package in.starhealth.employee_backend.controller;

import in.starhealth.employee_backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
