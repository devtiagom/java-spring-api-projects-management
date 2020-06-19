package com.myportfolio.projectsmanagement.repositories;

import com.myportfolio.projectsmanagement.domain.ProjectDeveloperDomain;
import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectDeveloperRepository extends JpaRepository<ProjectDeveloperDomain, Long> {
    @Transactional(readOnly=true)
    public List<ProjectDeveloperDomain> findByProject(ProjectDomain project);
}
