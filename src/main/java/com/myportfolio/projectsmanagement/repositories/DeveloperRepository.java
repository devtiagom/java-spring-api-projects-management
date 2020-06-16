package com.myportfolio.projectsmanagement.repositories;

import com.myportfolio.projectsmanagement.domain.DeveloperDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperDomain, Long> {}
