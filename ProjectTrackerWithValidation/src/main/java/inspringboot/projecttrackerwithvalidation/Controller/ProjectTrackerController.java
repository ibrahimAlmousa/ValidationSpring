package inspringboot.projecttrackerwithvalidation.Controller;

import inspringboot.projecttrackerwithvalidation.ApiResponse.ApiResponse;
import inspringboot.projecttrackerwithvalidation.Model.ProjectTracker;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project-tracker")
public class ProjectTrackerController {

    private final ArrayList<ProjectTracker> projectTrackers = new ArrayList<>();


    @PutMapping("/add")
    public ResponseEntity add(@Valid @RequestBody ProjectTracker project, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        projectTrackers.add(project);
        return ResponseEntity.status(201).body(new ApiResponse("added successfully"));
    }


    @GetMapping("/display")
    public ArrayList<ProjectTracker> display(){
        return projectTrackers;
    }


    @PutMapping("/update")
    public ApiResponse update(){
        return new ApiResponse("update successfully");
    }


    @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index){
        projectTrackers.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("delete successfully "));
    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @Valid @RequestBody ProjectTracker project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        projectTrackers.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("update successfully"));
    }


    @GetMapping("/search-by-title/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title){
        for (ProjectTracker p : projectTrackers){
            if (p.getTitle().equals(title)){
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("project not found"));
    }

    @GetMapping("/search-by-company/company")
    public ResponseEntity searchByCompany(@RequestBody String company){

        ArrayList<ProjectTracker> byName = new ArrayList<>();
        for (ProjectTracker p : projectTrackers){
            if (p.getCompanyName().equals(company)){
                byName.add(p);
            }
        }
        if (byName.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no projects for this company"));
        }
        return ResponseEntity.ok(byName);
    }
}
