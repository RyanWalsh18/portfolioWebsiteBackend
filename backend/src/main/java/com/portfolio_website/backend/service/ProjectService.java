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
        return projectRepository.findById(id);
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

    //Update whole Project item
    //----Arguments----
    // id (int) -> id of Project to update
    // updatedProject (Project) -> new details to update the Project item with
    public Optional<Project> updateProject(Integer id, Project updatedProject) {

        // Check if the Project exists
        Optional<Project> existingProject = projectRepository.findById(id);
        if  (existingProject.isEmpty()) {
            return Optional.empty();
        }

        // Validate request to ensure that all the fields are present
        if (updatedProject.getName() == null ||
            updatedProject.getDescription() == null ||
            updatedProject.getImageURL() == null){
            throw new IllegalArgumentException("All fields must be present for a PUT request");
        }

        // Update the Project with the new details
        existingProject.get().setName(updatedProject.getName());
        existingProject.get().setDescription(updatedProject.getDescription());
        existingProject.get().setImageURL(updatedProject.getImageURL());

        return Optional.of(projectRepository.save(existingProject.get()));
    }

    // Partially update Project
    //----Arguments----
    // id (int) -> id of Project to update
    // updatedProject (Project) -> new details to update the Project item with
    public Optional<Project> partiallyUpdateProject(Integer id, Project updatedProject) {
        // Check if the Project exists
        Optional<Project> existingProject = projectRepository.findById(id);
        if  (existingProject.isEmpty()) {
            return Optional.empty();
        }

        if (updatedProject.getName() == null &&
                updatedProject.getDescription() == null &&
                updatedProject.getImageURL() == null) {
            throw new IllegalArgumentException("At least one field must be provided");
        }


        // Check if a detail has been provided and update
        if (updatedProject.getName() != null){
            existingProject.get().setName(updatedProject.getName());
        }

        if (updatedProject.getDescription() != null){
            existingProject.get().setDescription(updatedProject.getDescription());
        }

        if (updatedProject.getImageURL() != null){
            existingProject.get().setImageURL(updatedProject.getImageURL());
        }

        return Optional.of(projectRepository.save(existingProject.get()));
    }

}
