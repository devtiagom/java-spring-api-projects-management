package com.myportfolio.projectsmanagement.controllers;

import com.myportfolio.projectsmanagement.dtos.developers.DeveloperGetDTO;
import com.myportfolio.projectsmanagement.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/devs")
public class DeveloperController {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DeveloperGetDTO>> index() {
        List<DeveloperGetDTO> developers = this.developerService
                .getDevelopers()
                .stream()
                .map(DeveloperGetDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(developers);
    }
}
