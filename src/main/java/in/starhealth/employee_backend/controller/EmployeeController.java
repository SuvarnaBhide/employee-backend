package in.starhealth.employee_backend.controller;

import in.starhealth.employee_backend.exception.ResourceNotFoundException;
import in.starhealth.employee_backend.model.Employee;
import in.starhealth.employee_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // build employee by id REST API
    @GetMapping("{ID}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable long ID) {
        Employee employee = employeeRepository.findById(ID).
                orElseThrow( () -> new ResourceNotFoundException("Employee does not exist with id " + ID));

        return ResponseEntity.ok(employee);
    }

    // build update employee REST API
    @PutMapping("{ID}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long ID, @RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(ID)
                .orElseThrow( () ->  new ResourceNotFoundException("Employee does not exist with id " + ID));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailID(employeeDetails.getEmailID());

        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    //build delete employee REST API
    @DeleteMapping("{ID}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long ID){

        Employee employee = employeeRepository.findById(ID)
                .orElseThrow( () -> new ResourceNotFoundException("Employee does not exist with id " + ID));


        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("students")
    @GetMapping()
    public ResponseEntity<Object> getStudents() {

        String url = "http://192.168.113.68:9050/students/";

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Object.class
        );
    }

}
