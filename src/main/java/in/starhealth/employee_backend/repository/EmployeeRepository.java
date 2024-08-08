package in.starhealth.employee_backend.repository;

import in.starhealth.employee_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // All CRUD DB methods
}
