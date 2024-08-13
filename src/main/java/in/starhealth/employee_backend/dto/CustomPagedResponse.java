package in.starhealth.employee_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CustomPagedResponse<T> {

    private long totalElements;
    private int totalPages;
    private int numberOfElementsPerPage;
    private int pageNumber;
    private List<T> content;
}
