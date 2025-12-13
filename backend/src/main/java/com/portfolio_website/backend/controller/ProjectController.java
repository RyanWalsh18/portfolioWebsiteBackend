package com.portfolio_website.backend.controller;


import com.portfolio_website.backend.entity.Project;
import com.portfolio_website.backend.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Project endpoint
@RestController
@RequestMapping("/project")
public class ProjectController {

    //create instance of ProjectService
    private final ProjectService projectService;

    //Constructor
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // GET "/project"
    // List all Projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // GET "/project/{id}"
    // List a specific Project
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
        Optional<Project> project = projectService.getProjectById(id);
        // Customer found -> return it with HTTP 200
        // Customer not found -> return HTTP 404 Not Found
        return project.map(ResponseEntity::ok) //HTTP 200
                .orElseGet(() -> ResponseEntity.notFound().build());// HTTP 404
    }

    // POST "/project"
    // create a new project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        // Call Service Layer to create new project
        Project createdProject = projectService.createProject(project);
        //return HTTP status
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // DELETE "/project/{id}"
    // delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProjectById(@PathVariable Integer id) {
        //store result of if the project was deleted
        boolean deleted = projectService.deleteProject(id);

        if(deleted) {
            // 200 OK to show successful deletion
            return ResponseEntity.ok().build();
        } else{
            // 404 Not Found if Project does not exist for deletion
            return ResponseEntity.notFound().build();
        }
    }

    // PUT "/project/{id}"
    // update entire Project item
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project updatedProject) {
        // Try catch to handle IllegalArgumentException from Bad Request
        try{
            //call service layer
            Optional<Project> project = projectService.updateProject(id,updatedProject);
            return project
                    .map(ResponseEntity::ok) // 200 OK -> Project updated successfully
                    .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found -> Project not found to update
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
