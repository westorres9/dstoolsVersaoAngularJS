package com.devsuperior.dstools.dto;

import com.devsuperior.dstools.entities.Department;

import javax.validation.constraints.NotBlank;

public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDTO(Department entity) {
        this.id = entity.getId();
        this.name = entity.getName();
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
}
