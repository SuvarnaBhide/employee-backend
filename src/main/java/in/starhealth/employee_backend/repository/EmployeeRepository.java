package in.starhealth.employee_backend.repository;

import in.starhealth.employee_backend.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    // All CRUD DB methods
}
