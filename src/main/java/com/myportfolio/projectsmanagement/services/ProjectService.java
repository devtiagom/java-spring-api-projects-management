package com.myportfolio.projectsmanagement.services;

import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import com.myportfolio.projectsmanagement.dtos.projects.ProjectSaveDTO;
import com.myportfolio.projectsmanagement.dtos.projects.ProjectUpdateDTO;
import com.myportfolio.projectsmanagement.repositories.ProjectRepository;
import com.myportfolio.projectsmanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDomain> getProjects() {
        return this.projectRepository.findAll();
    }

    public ProjectDomain getOneProject(Long id) {
        return this.projectRepository
                .findById(id)
                .orElseThrow(() -> {
                    return new ObjectNotFoundException(
                            "Objeto Não encontrado! Id: " + id + "Tipo: " + ProjectDomain.class.getName()
                    );
                });
    }

    public ProjectDomain createNewProject(ProjectSaveDTO projectDTO) {
        return this.projectRepository.save(formDTO(projectDTO));
    }

    public void updateProject(Long id, ProjectUpdateDTO projectDTO) {
        ProjectDomain projectFromDB = this.getOneProject(id);
        if (projectFromDB != null) {
            if (projectDTO.getName() != null) projectFromDB.setName(projectDTO.getName());
            if (projectDTO.getDescription() != null) projectFromDB.setDescription(projectDTO.getDescription());
            if (projectDTO.getClose()) projectFromDB.closeProject();
            this.projectRepository.save(projectFromDB);
        }
    }

    public void deleteProject(Long id) {
        ProjectDomain projectFromDB = this.getOneProject(id);
        if (projectFromDB != null) this.projectRepository.delete(projectFromDB);
    }

    private ProjectDomain formDTO(ProjectSaveDTO projectDTO) {
        return new ProjectDomain(projectDTO.getName(), projectDTO.getDescription());
    }
}
