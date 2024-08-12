package in.starhealth.employee_backend.model.entity;

import in.starhealth.employee_backend.model.pojo.EmployeePOJO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // Convert Entity to POJO
    public EmployeePOJO toPOJO() {
        return new EmployeePOJO(id, firstName, lastName, emailID);
    }

    // Create an Entity from POJO
    public static EmployeeEntity fromPOJO(EmployeePOJO pojo) {
        return new EmployeeEntity(pojo.getId(), pojo.getFirstName(), pojo.getLastName(), pojo.getEmailID());
    }
}

