package in.starhealth.employee_backend.controller;

import in.starhealth.employee_backend.exception.ResourceNotFoundException;
import in.starhealth.employee_backend.model.Employee;
import in.starhealth.employee_backend.repository.EmployeeRepository;
import in.starhealth.employee_backend.service.EmployeeService;
import in.starhealth.employee_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // build employee by id REST API
    @GetMapping("{ID}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long ID) {
        Employee employee = employeeService.getEmployeeById(ID);
        return ResponseEntity.ok(employee);
    }

    // build update employee REST API
    @PutMapping("{ID}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long ID, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(ID, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    //build delete employee REST API
    @DeleteMapping("{ID}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long ID) {
        employeeService.deleteEmployee(ID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("students")
//    public ResponseEntity<Object> getStudents() {
//        return studentService.getStudents();
//    }

//    @PostMapping("students")
//    public ResponseEntity<Object> createStudent(@RequestBody String payload) {
//        return studentService.createStudent(payload);
//    }

}
