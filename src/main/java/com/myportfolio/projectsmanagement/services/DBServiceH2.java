package com.myportfolio.projectsmanagement.services;

import com.myportfolio.projectsmanagement.domain.DeveloperDomain;
import com.myportfolio.projectsmanagement.domain.ProjectDeveloperDomain;
import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import com.myportfolio.projectsmanagement.domain.enums.DeveloperPosition;
import com.myportfolio.projectsmanagement.repositories.DeveloperRepository;
import com.myportfolio.projectsmanagement.repositories.ProjectDeveloperRepository;
import com.myportfolio.projectsmanagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServiceH2 {

    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;
    private final ProjectDeveloperRepository projectDeveloperRepository;

    @Autowired
    public DBServiceH2(
            ProjectRepository projectRepository,
            DeveloperRepository developerRepository,
            ProjectDeveloperRepository projectDeveloperRepository
    ) {
        this.projectRepository = projectRepository;
        this.developerRepository = developerRepository;
        this.projectDeveloperRepository = projectDeveloperRepository;
    }

    public void instantiateTestDatabase() {
        ProjectDomain proj01 = new ProjectDomain("ERP Empresa X", "Desenvolvimento de sistema ERP da empresa X.");
        ProjectDomain proj02 = new ProjectDomain("CRM Empresa Y", "Desenvolvimento de sistema CRM da empresa Y.");
        ProjectDomain proj03 = new ProjectDomain("E-Commerce", "Desenvolvimento de sistema E-Commerce.");
        this.projectRepository.saveAll(Arrays.asList(proj01, proj02, proj03));

        DeveloperDomain dev01 = new DeveloperDomain("John Doe", DeveloperPosition.JUNIOR);
        DeveloperDomain dev02 = new DeveloperDomain("Mark Z.", DeveloperPosition.ENGINEER);
        DeveloperDomain dev03 = new DeveloperDomain("Linus T.", DeveloperPosition.MANAGER);
        this.developerRepository.saveAll(Arrays.asList(dev01, dev02, dev03));

        ProjectDeveloperDomain projDev01 = new ProjectDeveloperDomain(proj03, dev01);
        ProjectDeveloperDomain projDev02 = new ProjectDeveloperDomain(proj01, dev02);
        ProjectDeveloperDomain projDev03 = new ProjectDeveloperDomain(proj02, dev02);
        ProjectDeveloperDomain projDev04 = new ProjectDeveloperDomain(proj01, dev03);
        ProjectDeveloperDomain projDev05 = new ProjectDeveloperDomain(proj02, dev03);
        ProjectDeveloperDomain projDev06 = new ProjectDeveloperDomain(proj03, dev03);
        this.projectDeveloperRepository.saveAll(Arrays.asList(
                projDev01,
                projDev02,
                projDev03,
                projDev04,
                projDev05,
                projDev06
        ));
    }
}
