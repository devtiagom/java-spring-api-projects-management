package com.myportfolio.projectsmanagement.services;

import com.myportfolio.projectsmanagement.domain.DeveloperDomain;
import com.myportfolio.projectsmanagement.domain.ProjectDeveloperDomain;
import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import com.myportfolio.projectsmanagement.dtos.projects.ProjectSaveDTO;
import com.myportfolio.projectsmanagement.dtos.projects.ProjectUpdateDTO;
import com.myportfolio.projectsmanagement.repositories.ProjectDeveloperRepository;
import com.myportfolio.projectsmanagement.repositories.ProjectRepository;
import com.myportfolio.projectsmanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectDeveloperRepository projectDeveloperRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectDeveloperRepository projectDeveloperRepository) {
        this.projectRepository = projectRepository;
        this.projectDeveloperRepository = projectDeveloperRepository;
    }

    public Page<ProjectDomain> getProjects(Integer page, Integer size, String direction, String orderBy) {
        return this.projectRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy));
    }

    public ProjectDomain getOneProject(Long id) {
        return this.projectRepository
                .findById(id)
                .orElseThrow(() -> {
                    return new ObjectNotFoundException(
                            "Objeto Não encontrado! Id: " + id + " Tipo: " + ProjectDomain.class.getName()
                    );
                });
    }

    public ProjectDomain createNewProject(ProjectSaveDTO projectDTO) {
        return this.projectRepository.save(fromDTO(projectDTO));
    }

    public void updateProject(Long id, ProjectUpdateDTO projectDTO) {
        ProjectDomain projectFromDB = this.getOneProject(id);
        if (projectFromDB != null) {
            if (projectDTO.getName() != null) projectFromDB.setName(projectDTO.getName());
            if (projectDTO.getDescription() != null) projectFromDB.setDescription(projectDTO.getDescription());
            if (projectDTO.getClose() != null) if (projectDTO.getClose()) projectFromDB.closeProject();
            this.projectRepository.save(projectFromDB);
        }
    }

    public void deleteProject(Long id) {
        ProjectDomain projectFromDB = this.getOneProject(id);
        if (projectFromDB != null) this.projectRepository.delete(projectFromDB);
    }

    private ProjectDomain fromDTO(ProjectSaveDTO projectDTO) {
        return new ProjectDomain(projectDTO);
    }

    public List<DeveloperDomain> getProjectDevelopers(Long id) {
        ProjectDomain projectFromDB = this.getOneProject(id);
        List<DeveloperDomain> devs = new ArrayList<>();
        if (projectFromDB != null) {
            List<ProjectDeveloperDomain> projDevs = this.projectDeveloperRepository.findByProject(projectFromDB);
            devs = projDevs.stream().map(projDev -> projDev.getDeveloper()).collect(Collectors.toList());
        }
        return devs;
    }
}
