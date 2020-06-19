package com.myportfolio.projectsmanagement.domain;

import com.myportfolio.projectsmanagement.domain.enums.DeveloperPosition;
import com.myportfolio.projectsmanagement.dtos.developers.DeveloperSaveDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity @Table(name = "developers")
public class DeveloperDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(generator = "developers_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer positionCode;

    @OneToMany(mappedBy = "developer")
    private Set<ProjectDeveloperDomain> projectDevelopers = new HashSet<>();

    public DeveloperDomain() {}

    public DeveloperDomain(String fullName, DeveloperPosition position) {
        this.fullName = fullName;
        this.positionCode = position.getPositionCode();
    }

    public DeveloperDomain(DeveloperSaveDTO developerDTO) {
        this.fullName = developerDTO.getFullName();
        this.positionCode = developerDTO.getPosition().getPositionCode();
    }

    public Long getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public DeveloperPosition getPosition() {
        return DeveloperPosition.toEnum(this.positionCode);
    }

    public void setPosition(DeveloperPosition position) {
        this.positionCode = position.getPositionCode();
    }

    public Set<ProjectDeveloperDomain> getProjectDevelopers() {
        return projectDevelopers;
    }

    public void addProjectDevelopers(ProjectDeveloperDomain projectDeveloper) {
        this.projectDevelopers.add(projectDeveloper);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperDomain that = (DeveloperDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeveloperDomain{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", positionCode=" + positionCode +
                ", projectDevelopers=" + projectDevelopers +
                '}';
    }
}
