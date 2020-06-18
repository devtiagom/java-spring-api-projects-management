package com.myportfolio.projectsmanagement.services;

import com.myportfolio.projectsmanagement.domain.DeveloperDomain;
import com.myportfolio.projectsmanagement.dtos.developers.DeveloperSaveDTO;
import com.myportfolio.projectsmanagement.repositories.DeveloperRepository;
import com.myportfolio.projectsmanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Page<DeveloperDomain> getDevelopers(Integer page, Integer size, String direction, String orderBy) {
        return this.developerRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy));
    }

    public DeveloperDomain getOneDeveloper(Long id) {
        return this.developerRepository
                .findById(id)
                .orElseThrow(() -> {
                    return new ObjectNotFoundException(
                            "Objeto não encontrado Id: " + id + " Tipo: " + DeveloperDomain.class.getName()
                    );
                });
    }

    public DeveloperDomain createNewDeveloper(DeveloperSaveDTO developerDTO) {
        return this.developerRepository.save(fromDTO(developerDTO));
    }

    public void updateDeveloper(Long id, DeveloperSaveDTO developerDTO) {
        DeveloperDomain developerFromDB = this.getOneDeveloper(id);
        if (developerFromDB != null) {
            if (developerDTO.getFullName() != null) developerFromDB.setFullName(developerDTO.getFullName());
            if (developerDTO.getPosition() != null) developerFromDB.setPosition(developerDTO.getPosition());
            this.developerRepository.save(developerFromDB);
        }
    }

    public void deleteDeveloper(Long id) {
        DeveloperDomain developerFromDB = this.getOneDeveloper(id);
        if (developerFromDB != null) this.developerRepository.delete(developerFromDB);
    }

    private DeveloperDomain fromDTO(DeveloperSaveDTO developerDTO) {
        return new DeveloperDomain(developerDTO);
    }
}
