package com.myportfolio.projectsmanagement.controllers;

import com.myportfolio.projectsmanagement.domain.DeveloperDomain;
import com.myportfolio.projectsmanagement.dtos.developers.DeveloperGetDTO;
import com.myportfolio.projectsmanagement.dtos.developers.DeveloperSaveDTO;
import com.myportfolio.projectsmanagement.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/devs")
public class DeveloperController {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<DeveloperGetDTO>> index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "fullName") String orderBy
    ) {
        Page<DeveloperGetDTO> developersDTO = this.developerService
                .getDevelopers(page, size, direction, orderBy)
                .map(DeveloperGetDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(developersDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<DeveloperGetDTO> show(@PathVariable Long id) {
        DeveloperGetDTO developer = new DeveloperGetDTO(this.developerService.getOneDeveloper(id));
        return ResponseEntity.status(HttpStatus.OK).body(developer);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> store(@Valid @RequestBody DeveloperSaveDTO developerDTO) {
        DeveloperDomain developerDomain = this.developerService.createNewDeveloper(developerDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(developerDomain.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody DeveloperSaveDTO developerDTO) {
        this.developerService.updateDeveloper(id, developerDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        this.developerService.deleteDeveloper(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
