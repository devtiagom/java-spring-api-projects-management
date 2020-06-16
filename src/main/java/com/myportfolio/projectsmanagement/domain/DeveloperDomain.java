package com.myportfolio.projectsmanagement.domain;

import com.myportfolio.projectsmanagement.domain.enums.DeveloperPosition;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "developers")
public class DeveloperDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(generator = "developers_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer positionCode;

    public DeveloperDomain() {}

    public DeveloperDomain(String fullName, DeveloperPosition position) {
        this.fullName = fullName;
        this.positionCode = position.getPositionCode();
    }

    public Long getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperDomain that = (DeveloperDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeveloperDomain{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", positionCode=" + positionCode +
                '}';
    }
}
