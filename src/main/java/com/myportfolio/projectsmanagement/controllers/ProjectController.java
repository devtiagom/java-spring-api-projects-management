package com.myportfolio.projectsmanagement.controllers;

import com.myportfolio.projectsmanagement.dtos.projects.ProjectGetDTO;
import com.myportfolio.projectsmanagement.repositories.ProjectRepository;
import com.myportfolio.projectsmanagement.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProjectGetDTO>> index() {
        List<ProjectGetDTO> projects = this.projectService.findAll().stream().map(ProjectGetDTO::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }
}
