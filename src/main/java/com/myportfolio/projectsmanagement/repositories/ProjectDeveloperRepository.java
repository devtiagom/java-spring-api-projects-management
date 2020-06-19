package com.myportfolio.projectsmanagement.repositories;

import com.myportfolio.projectsmanagement.domain.ProjectDeveloperDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDeveloperRepository extends JpaRepository<ProjectDeveloperDomain, Long> {}
