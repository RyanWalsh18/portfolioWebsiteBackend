package com.portfolio_website.backend.controller;


import com.portfolio_website.backend.entity.Project;
import com.portfolio_website.backend.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
