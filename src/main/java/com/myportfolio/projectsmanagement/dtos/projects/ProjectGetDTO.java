package com.myportfolio.projectsmanagement.dtos.projects;

import com.myportfolio.projectsmanagement.domain.ProjectDomain;
import com.myportfolio.projectsmanagement.domain.enums.ProjectLifeCycle;

import java.io.Serializable;
import java.util.Date;

public class ProjectGetDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Date startedAt;
    private Date finishedAt;
    private Integer status;

    public ProjectGetDTO() {}

    public ProjectGetDTO(ProjectDomain projectDomain) {
        this.id = projectDomain.getId();
        this.name = projectDomain.getName();
        this.description = projectDomain.getDescription();
        this.startedAt = projectDomain.getStartedAt();
        this.finishedAt = projectDomain.getFinishedAt();
        this.status = projectDomain.getStatus().getStatusCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
