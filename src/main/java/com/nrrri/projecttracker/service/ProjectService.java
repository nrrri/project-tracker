package com.nrrri.projecttracker.service;

import com.nrrri.projecttracker.entity.Project;
import com.nrrri.projecttracker.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public  ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> getProjectById (Long id) {
        return projectRepository.findById(id);
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));

        existingProject.setName(updatedProject.getName());
        existingProject.setStage(updatedProject.getStage());
        existingProject.setStartDate(updatedProject.getStartDate());
        existingProject.setAddress(updatedProject.getAddress());
        existingProject.setProjectType(updatedProject.getProjectType());
        existingProject.setFloors(updatedProject.getFloors());
        existingProject.setArea(updatedProject.getArea());
        existingProject.setDescription(updatedProject.getDescription());

        return projectRepository.save(existingProject);
    }

    public void deleteProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }

        projectRepository.deleteById(id);
    }
}
