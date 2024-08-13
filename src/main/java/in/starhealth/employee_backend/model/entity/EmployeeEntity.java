package in.starhealth.employee_backend.model.entity;

import in.starhealth.employee_backend.model.pojo.EmployeePOJO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String emailID;

    // Convert Entity to POJO using BeanUtils
    public EmployeePOJO toPOJO() {
        EmployeePOJO pojo = new EmployeePOJO();
        BeanUtils.copyProperties(this, pojo);
        return pojo;
    }

    // Create an Entity from POJO using BeanUtils
    public static EmployeeEntity fromPOJO(EmployeePOJO pojo) {
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(pojo, entity);
        return entity;
    }
}

