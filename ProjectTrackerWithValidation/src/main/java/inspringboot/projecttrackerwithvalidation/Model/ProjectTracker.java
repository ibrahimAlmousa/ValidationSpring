package inspringboot.projecttrackerwithvalidation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTracker {

    @NotEmpty(message = "ID cannot be empty")
    @Size(min = 2, message = "ID length must be more than 2")
    private String ID;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 8, message = "Title length must be more than 8")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 15, message = "Description length must be more than 15")
    private String description;

    @NotEmpty(message = "Status cannot be empty")
    @Pattern(regexp = "Not Started|In Progress|Completed", message = "Status must be Not Started, In Progress, or Completed only")
    private String status;

    @NotEmpty(message = "Company name cannot be empty")
    @Size(min = 6, message = "Company name length must be more than 6")
    private String companyName;
}
