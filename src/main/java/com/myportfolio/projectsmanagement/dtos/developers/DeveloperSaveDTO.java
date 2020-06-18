package com.myportfolio.projectsmanagement.dtos.developers;

import com.myportfolio.projectsmanagement.domain.enums.DeveloperPosition;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DeveloperSaveDTO {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 6, max = 30, message = "O campo nome completo deve ter entre 6 e 30 caracteres")
    private String fullName;

    @NotNull(message = "Campo obrigatório")
    @Min(value = 1, message = "positionCode deve ser maior que 0")
    @Max(value = 3, message = "positionCode deve ser menor que 4")
    private Integer positionCode;

    public DeveloperSaveDTO() {}

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
