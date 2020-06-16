package com.myportfolio.projectsmanagement.domain.enums;

public enum DeveloperPosition {
    JUNIOR(1, "Junior software developer"),
    ENGINEER(2, "Software Engineer"),
    MANAGER(3, "Project manager");

    private static final String INVALID_POSITION_CODE = "Código inválido: ";

    private final Integer positionCode;
    private final String positionDescription;

    DeveloperPosition(Integer positionCode, String positionDescription) {
        this.positionCode = positionCode;
        this.positionDescription = positionDescription;
    }

    public Integer getPositionCode() {
        return positionCode;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public static DeveloperPosition toEnum(Integer positionCode) {
        if (positionCode == null) return null;

        for (DeveloperPosition developerPosition : DeveloperPosition.values()) {
            if (positionCode.equals(developerPosition.positionCode)) return developerPosition;
        }

        throw new IllegalArgumentException(DeveloperPosition.INVALID_POSITION_CODE + positionCode);
    }
}
