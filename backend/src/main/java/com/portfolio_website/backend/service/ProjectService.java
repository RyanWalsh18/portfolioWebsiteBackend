package com.portfolio_website.backend.service;


import com.portfolio_website.backend.entity.Project;
import com.portfolio_website.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    //initialise Customer Repo
    private final ProjectRepository projectRepository;

    //Constructor
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    //return all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    //Return specific Project
    //----Arguements----
    // id (Int) -> id of the project to search for
    public Optional<Project> getProjectById(Integer id) {
        Optional<Project> project = projectRepository.findById(id);
        return project;
    }
}
