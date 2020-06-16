package com.myportfolio.projectsmanagement.services;

import com.myportfolio.projectsmanagement.domain.DeveloperDomain;
import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import com.myportfolio.projectsmanagement.domain.enums.DeveloperPosition;
import com.myportfolio.projectsmanagement.repositories.DeveloperRepository;
import com.myportfolio.projectsmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServiceH2 {

    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;

    @Autowired
    public DBServiceH2(ProjectRepository projectRepository, DeveloperRepository developerRepository) {
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
    }

    public void instantiateTestDatabase() {
        ProjectDomain project01 = new ProjectDomain("ERP Empresa X", "Desenvolvimento de sistema ERP da empresa X.");
        ProjectDomain project02 = new ProjectDomain("CRM Empresa Y", "Desenvolvimento de sistema CRM da empresa Y.");
        ProjectDomain project03 = new ProjectDomain("E-Commerce", "Desenvolvimento de sistema E-Commerce.");
        this.projectRepository.saveAll(Arrays.asList(project01, project02, project03));

        DeveloperDomain dev01 = new DeveloperDomain("John Doe", DeveloperPosition.JUNIOR);
        DeveloperDomain dev02 = new DeveloperDomain("Mark Z.", DeveloperPosition.ENGINEER);
        DeveloperDomain dev03 = new DeveloperDomain("Linus T.", DeveloperPosition.MANAGER);
        this.developerRepository.saveAll(Arrays.asList(dev01, dev02, dev03));
    }
}
