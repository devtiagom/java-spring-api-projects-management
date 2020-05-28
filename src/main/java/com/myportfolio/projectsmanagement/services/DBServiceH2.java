package com.myportfolio.projectsmanagement.services;

import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import com.myportfolio.projectsmanagement.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServiceH2 {

    private final ProjectRepository projectRepository;

    public DBServiceH2(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void instantiateTestDatabase() {
        ProjectDomain project01 = new ProjectDomain("ERP Empresa X", "Desenvolvimento de sistema ERP da empresa X.");
        ProjectDomain project02 = new ProjectDomain("CRM Empresa Y", "Desenvolvimento de sistema CRM da empresa Y.");
        ProjectDomain project03 = new ProjectDomain("E-Commerce", "Desenvolvimento de sistema E-Commerce.");
        this.projectRepository.saveAll(Arrays.asList(project01, project02, project03));
    }
}
