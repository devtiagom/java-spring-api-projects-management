package com.myportfolio.projectsmanagement.domain;

import com.myportfolio.projectsmanagement.domain.enums.ProjectLifeCycle;
import com.myportfolio.projectsmanagement.dtos.projects.ProjectSaveDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity @Table(name = "projects")
public class ProjectDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(generator = "projects_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "started_at", nullable = false)
    private Date startedAt;

    @Column(name = "finished_at")
    private Date finishedAt;

    @Column(nullable = false)
    private Integer status;

    public ProjectDomain() {
        this.startedAt = new Date(System.currentTimeMillis());
        this.status = ProjectLifeCycle.IN_PROGRESS.getStatusCode();
    }

    public ProjectDomain(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public ProjectDomain(ProjectSaveDTO projectDTO) {
        this();
        this.name = projectDTO.getName();
        this.description = projectDTO.getDescription();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public ProjectLifeCycle getStatus() {
        return ProjectLifeCycle.toEnum(this.status);
    }

    public void setStatus(ProjectLifeCycle status) {
        this.status = status.getStatusCode();
    }

    public ProjectLifeCycle closeProject() {
        this.status = ProjectLifeCycle.FINISHED.getStatusCode();
        this.finishedAt = new Date(System.currentTimeMillis());
        return ProjectLifeCycle.toEnum(this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectDomain)) return false;
        ProjectDomain that = (ProjectDomain) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ProjectDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startedAt=" + startedAt +
                ", finishedAt=" + finishedAt +
                ", status=" + status +
                '}';
    }
}
