package com.myportfolio.projectsmanagement.dtos.projects;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ProjectSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório.")
    @Length(min = 3, max = 30, message = "O campo nome deve ter entre 3 e 30 caracteres.")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório.")
    @Length(min = 10, max = 120, message = "O campo descrição deve ter entre 10 e 120 caracteres.")
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
