package com.myportfolio.projectsmanagement.dtos.developers;

import com.myportfolio.projectsmanagement.domain.enums.DeveloperPosition;

public class DeveloperUpdateDTO {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private Integer positionCode;

    public DeveloperUpdateDTO() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public DeveloperPosition getPosition() {
        return DeveloperPosition.toEnum(this.positionCode);
    }

    public void setPositionCode(Integer positionCode) {
        this.positionCode = positionCode;
    }
}
