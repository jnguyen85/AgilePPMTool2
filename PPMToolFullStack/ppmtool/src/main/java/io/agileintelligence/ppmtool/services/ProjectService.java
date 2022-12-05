package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService() {

    }

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exist");
        }
    }


    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if (project == null) throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' does not exist");
        return projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    }

    public Iterable<Project> findAllProject() {
        return projectRepository.findAll();
    }
}
