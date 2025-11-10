package inspringboot.eventsystemwithvalidation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @NotEmpty(message = "ID cannot be empty")
    @Size(min = 2, message = "ID length must be more than 2")
    private String ID;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 15, message = "Description length must be more than 15")
    private String description;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 25, message = "Capacity must be more than 25")
    private Integer capacity;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
