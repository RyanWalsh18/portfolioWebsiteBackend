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
    //----Arguments----
    // id (Int) -> id of the project to search for
    public Optional<Project> getProjectById(Integer id) {
        Optional<Project> project = projectRepository.findById(id);
        return project;
    }

    // Create new Project
    //----Arguments----
    // project (Project) -> Project to be created
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Delete Project
    //----Arguments----
    // id (int) -> id of project to be deleted
    public boolean deleteProject(Integer id) {
        //check if the project exists
        if (projectRepository.existsById(id)) {
            //delete the project
            projectRepository.deleteById(id);
            // return true to show the project has been deleted
            return true;
        }
        //return false if the project is not found (therefore not deleted)
        return false;
    }
}
