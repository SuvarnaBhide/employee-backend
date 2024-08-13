package in.starhealth.employee_backend.service;

import in.starhealth.employee_backend.dto.CustomPagedResponse;
import in.starhealth.employee_backend.exception.ResourceNotFoundException;
import in.starhealth.employee_backend.model.entity.EmployeeEntity;
import in.starhealth.employee_backend.model.pojo.EmployeePOJO;
import in.starhealth.employee_backend.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
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
    public CustomPagedResponse<EmployeePOJO> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<EmployeeEntity> employeeEntities = employeeRepository.findAll(pageable);

        List<EmployeePOJO> employeePOJOs = employeeEntities.stream()
                .map(entity -> {
                    EmployeePOJO pojo = new EmployeePOJO();
                    BeanUtils.copyProperties(entity, pojo);
                    return pojo;
                })
                .collect(Collectors.toList());

        return new CustomPagedResponse<>(
                employeeEntities.getTotalElements(),
                employeeEntities.getTotalPages(),
                employeeEntities.getNumberOfElements(),
                employeeEntities.getNumber() + 1,
                employeePOJOs
        );
    }

    // Create a new employee and convert to POJO
    public EmployeePOJO createEmployee(EmployeePOJO employeePOJO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeePOJO, employeeEntity);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);

        EmployeePOJO savedEmployeePOJO = new EmployeePOJO();
        BeanUtils.copyProperties(savedEntity, savedEmployeePOJO);
        return savedEmployeePOJO;
    }

    // Get employee by ID and convert to POJO
    public EmployeePOJO getEmployeeById(long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id " + id));

        EmployeePOJO employeePOJO = new EmployeePOJO();
        BeanUtils.copyProperties(employeeEntity, employeePOJO);

        return employeePOJO;
    }

    // Update employee details and convert to POJO
    public EmployeePOJO updateEmployee(long id, EmployeePOJO employeeDetails) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id " + id));

        // Copy properties from POJO to existing entity
        BeanUtils.copyProperties(employeeDetails, existingEmployee, "id"); // Exclude the ID field

        EmployeeEntity updatedEntity = employeeRepository.save(existingEmployee);

        EmployeePOJO updatedEmployeePOJO = new EmployeePOJO();
        BeanUtils.copyProperties(updatedEntity, updatedEmployeePOJO);

        return updatedEmployeePOJO;
    }

    // Delete employee by ID
    public void deleteEmployee(long id) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id " + id));
        employeeRepository.delete(existingEmployee);
    }
}
