package com.portfolio_website.backend.controller;


import com.portfolio_website.backend.entity.Project;
import com.portfolio_website.backend.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

}
