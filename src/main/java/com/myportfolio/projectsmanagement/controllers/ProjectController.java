package com.myportfolio.projectsmanagement.controllers;

import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import com.myportfolio.projectsmanagement.dtos.ObjectIdDTO;
import com.myportfolio.projectsmanagement.dtos.developers.DeveloperGetDTO;
import com.myportfolio.projectsmanagement.dtos.developers.DeveloperSaveDTO;
import com.myportfolio.projectsmanagement.dtos.projects.ProjectGetDTO;
import com.myportfolio.projectsmanagement.dtos.projects.ProjectSaveDTO;
import com.myportfolio.projectsmanagement.dtos.projects.ProjectUpdateDTO;
import com.myportfolio.projectsmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProjectGetDTO>> index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        Page<ProjectGetDTO> projectsDTO = this.projectService
                .getProjects(page, size, direction, orderBy)
                .map(ProjectGetDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(projectsDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProjectGetDTO> show(@PathVariable Long id) {
        ProjectGetDTO projectDTO = new ProjectGetDTO(this.projectService.getOneProject(id));
        return ResponseEntity.status(HttpStatus.OK).body(projectDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> store(@Valid @RequestBody ProjectSaveDTO projectDTO) {
        ProjectDomain projectDomain = this.projectService.createNewProject(projectDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(projectDomain.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDTO projectDTO) {
        this.projectService.updateProject(id, projectDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        this.projectService.deleteProject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{projectId}/devs", method = RequestMethod.GET)
    public ResponseEntity<List<DeveloperGetDTO>> getProjectDevelopers(@PathVariable Long projectId) {
        List<DeveloperGetDTO> projectDevelopers = this.projectService
                .getProjectDevelopers(projectId)
                .stream()
                .map(DeveloperGetDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(projectDevelopers);
    }

    @RequestMapping(value = "/{projectId}/devs", method = RequestMethod.POST)
    public ResponseEntity<Void> addDeveloperToProject(
            @PathVariable Long projectId,
            @RequestBody ObjectIdDTO developerIdDTO
    ) {
        this.projectService.addDeveloperToProject(projectId, developerIdDTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
