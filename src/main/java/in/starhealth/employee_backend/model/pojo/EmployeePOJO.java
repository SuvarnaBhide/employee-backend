package in.starhealth.employee_backend.model.pojo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeePOJO {

    private long id;
    private String firstName;
    private String lastName;
    private String emailID;

}

