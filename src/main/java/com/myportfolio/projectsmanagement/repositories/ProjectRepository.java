package com.myportfolio.projectsmanagement.repositories;

import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectDomain, Long> {}
