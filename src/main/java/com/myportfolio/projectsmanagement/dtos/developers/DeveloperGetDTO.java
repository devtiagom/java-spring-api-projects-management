package com.myportfolio.projectsmanagement.dtos.developers;

import com.myportfolio.projectsmanagement.domain.DeveloperDomain;
import com.myportfolio.projectsmanagement.domain.enums.DeveloperPosition;

import java.io.Serializable;

public class DeveloperGetDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullName;
    private Integer positionCode;

    public DeveloperGetDTO() {}

    public DeveloperGetDTO(DeveloperDomain developerDomain) {
        this.id = developerDomain.getId();
        this.fullName = developerDomain.getFullName();
        this.positionCode = developerDomain.getPosition().getPositionCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
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
}
