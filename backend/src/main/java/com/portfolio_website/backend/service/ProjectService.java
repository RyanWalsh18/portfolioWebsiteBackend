package com.portfolio_website.backend.service;


import com.portfolio_website.backend.entity.Project;
import com.portfolio_website.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
