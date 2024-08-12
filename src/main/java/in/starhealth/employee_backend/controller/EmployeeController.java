package in.starhealth.employee_backend.controller;

import in.starhealth.employee_backend.exception.ResourceNotFoundException;
import in.starhealth.employee_backend.model.Employee;
import in.starhealth.employee_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Updated getAllEmployees method to handle pagination
    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Page<Employee> employees = employeeService.getAllEmployees(page, size);
        return ResponseEntity.ok(employees);
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
}
