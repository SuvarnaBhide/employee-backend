package in.starhealth.employee_backend.service;

import in.starhealth.employee_backend.exception.ResourceNotFoundException;
import in.starhealth.employee_backend.model.entity.EmployeeEntity;
import in.starhealth.employee_backend.model.pojo.EmployeePOJO;
import in.starhealth.employee_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Fetch all employees with pagination and convert to POJO
    public List<EmployeePOJO> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeEntity> employeeEntities = employeeRepository.findAll(pageable);

        //Convert entity to POJO and return it
        return employeeEntities.stream().map(EmployeeEntity::toPOJO).collect(Collectors.toList());
    }

    // Create a new employee and convert to POJO
    public EmployeePOJO createEmployee(EmployeePOJO employeePOJO) {
        EmployeeEntity employeeEntity = EmployeeEntity.fromPOJO(employeePOJO);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return savedEntity.toPOJO();
    }

    // Get employee by ID and convert to POJO
    public EmployeePOJO getEmployeeById(long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id " + id));
        return employeeEntity.toPOJO();
    }

    // Update employee details and convert to POJO
    public EmployeePOJO updateEmployee(long id, EmployeePOJO employeeDetails) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id " + id));

        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmailID(employeeDetails.getEmailID());

        EmployeeEntity updatedEntity = employeeRepository.save(existingEmployee);
        return updatedEntity.toPOJO();
    }

    // Delete employee by ID
    public void deleteEmployee(long id) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id " + id));
        employeeRepository.delete(existingEmployee);
    }
}
