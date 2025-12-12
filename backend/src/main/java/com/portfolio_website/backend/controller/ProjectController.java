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
        // Customer found -> return it with HTTP 400
        // Customer not found -> return HTTP 404 Not Found
        return project.map(ResponseEntity::ok) //HTTP 400
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
            // return 204 No Content to show successful deletion
            return ResponseEntity.noContent().build();
        } else{
            // return 404 Not Found if Project does not exist for deletion
            return ResponseEntity.notFound().build();
        }
    }
}
