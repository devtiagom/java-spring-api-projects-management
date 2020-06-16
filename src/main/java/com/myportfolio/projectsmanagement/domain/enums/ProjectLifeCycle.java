package com.myportfolio.projectsmanagement.domain.enums;

public enum ProjectLifeCycle {
    IN_PROGRESS(1, "Project in progress"),
    FINISHED(2, "Project finished");

    private static final String INVALID_STATUS_CODE = "Código inválido: ";

    private final Integer statusCode;
    private final String statusDescription;

    ProjectLifeCycle(Integer statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public static ProjectLifeCycle toEnum(Integer statusCode) {
        if (statusCode == null) return null;

        for (ProjectLifeCycle projectLifeCycle : ProjectLifeCycle.values()) {
            if (statusCode.equals(projectLifeCycle.statusCode)) return projectLifeCycle;
        }

        throw new IllegalArgumentException(ProjectLifeCycle.INVALID_STATUS_CODE + statusCode);
    }
}
