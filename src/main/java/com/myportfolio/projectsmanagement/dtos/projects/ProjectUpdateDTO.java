package com.myportfolio.projectsmanagement.dtos.projects;

import java.io.Serializable;

public class ProjectUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Boolean close;

    public ProjectUpdateDTO() {}

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

    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }
}
