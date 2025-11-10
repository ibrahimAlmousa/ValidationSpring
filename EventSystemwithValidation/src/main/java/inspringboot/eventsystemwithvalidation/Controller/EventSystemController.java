package inspringboot.eventsystemwithvalidation.Controller;

import inspringboot.eventsystemwithvalidation.ApiResponse.ApiResponse;
import inspringboot.eventsystemwithvalidation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventSystemController {

    ArrayList<Event> events = new ArrayList<>();

    @PutMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        events.add(event);
        return ResponseEntity.status(201).body(new ApiResponse("added successfully"));
    }

    @GetMapping("/display")
    public ArrayList<Event> display() {
        return events;
    }

    @PutMapping("/update/{index}")
    public ResponseEntity update(@PathVariable int index,
                                 @Valid @RequestBody Event event,
                                 Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("update successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("delete successfully"));
    }

    @PatchMapping("/change-capacity/{index}")
    public ResponseEntity changeCapacity(@PathVariable int index,
                                         @Valid @RequestBody Event updatedEvent,
                                         Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        Event existing = events.get(index);
        existing.setCapacity(updatedEvent.getCapacity());
        return ResponseEntity.status(200).body(new ApiResponse("capacity changed"));
    }

    @GetMapping("/search-by-id/{id}")
    public ResponseEntity searchById(@PathVariable String id) {
        for (Event e : events) {
            if (e.getID().equals(id)) {
                return ResponseEntity.ok(e);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("event not found"));
    }
}
