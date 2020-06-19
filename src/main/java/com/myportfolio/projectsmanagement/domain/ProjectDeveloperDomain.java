package com.myportfolio.projectsmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "project_developers")
public class ProjectDeveloperDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(generator = "project_developers_seq", strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectDomain project;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private DeveloperDomain developer;

    public ProjectDeveloperDomain() {}

    public ProjectDeveloperDomain(ProjectDomain project, DeveloperDomain developer) {
        this.project = project;
        this.developer = developer;
    }

    public Long getId() {
        return id;
    }

    public ProjectDomain getProject() {
        return project;
    }

    public void setProject(ProjectDomain project) {
        this.project = project;
    }

    public DeveloperDomain getDeveloper() {
        return developer;
    }

    public void setDeveloper(DeveloperDomain developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDeveloperDomain that = (ProjectDeveloperDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProjectDeveloperDomain{" +
                "id=" + id +
                ", project=" + project +
                ", developer=" + developer +
                '}';
    }
}
