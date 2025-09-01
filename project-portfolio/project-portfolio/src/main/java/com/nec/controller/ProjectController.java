package com.nec.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nec.model.Project;
import com.nec.service.ProjectService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173") // allow React dev server
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<Project> all() {
        return service.getAllProjects();
    }

    @PostMapping
    public ResponseEntity<Project> create(@Valid @RequestBody Project p) {
        Project created = service.createProject(p);
        return ResponseEntity.created(URI.create("/api/projects/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable("id") Long id) {
        return service.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable("id") Long id, @Valid @RequestBody Project p) {
        Project updated = service.updateProject(id, p);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
