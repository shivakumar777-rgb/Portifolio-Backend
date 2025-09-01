package com.nec.service;

import org.springframework.stereotype.Service;

import com.nec.model.Project;
import com.nec.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Project createProject(Project p) {
        return repository.save(p);
    }

    public Optional<Project> getProjectById(Long id) {
        return repository.findById(id);
    }

    public Project updateProject(Long id, Project updated) {
        return repository.findById(id).map(p -> {
            p.setTitle(updated.getTitle());
            p.setDescription(updated.getDescription());
            return repository.save(p);
        }).orElseGet(() -> {
            updated.setId(id);
            return repository.save(updated);
        });
    }

    public void deleteProject(Long id) {
        repository.deleteById(id);
    }
}
