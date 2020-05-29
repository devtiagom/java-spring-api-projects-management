package com.myportfolio.projectsmanagement.dtos.projects;

import java.io.Serializable;

public class ProjectSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

    public ProjectSaveDTO() {}

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
}
