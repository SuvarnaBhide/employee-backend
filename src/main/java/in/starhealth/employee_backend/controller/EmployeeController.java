package in.starhealth.employee_backend.controller;

import in.starhealth.employee_backend.model.pojo.EmployeePOJO;
import in.starhealth.employee_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Updated getAllEmployees method to handle pagination
    @GetMapping
    public ResponseEntity<Page<EmployeePOJO>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Page<EmployeePOJO> employees = employeeService.getAllEmployees(page, size);
        return ResponseEntity.ok(employees);
    }

    // Build create employee REST API
    @PostMapping
    public ResponseEntity<EmployeePOJO> createEmployee(@RequestBody EmployeePOJO employeePOJO) {
        EmployeePOJO createdEmployee = employeeService.createEmployee(employeePOJO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    // Build employee by ID REST API
    @GetMapping("{ID}")
    public ResponseEntity<EmployeePOJO> getEmployeeById(@PathVariable long ID) {
        EmployeePOJO employee = employeeService.getEmployeeById(ID);
        return ResponseEntity.ok(employee);
    }

    // Build update employee REST API
    @PutMapping("{ID}")
    public ResponseEntity<EmployeePOJO> updateEmployee(@PathVariable long ID, @RequestBody EmployeePOJO employeeDetails) {
        EmployeePOJO updatedEmployee = employeeService.updateEmployee(ID, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Build delete employee REST API
    @DeleteMapping("{ID}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long ID) {
        employeeService.deleteEmployee(ID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
