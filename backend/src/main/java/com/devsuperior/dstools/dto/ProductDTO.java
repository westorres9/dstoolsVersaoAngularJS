package com.devsuperior.dstools.dto;

import com.devsuperior.dstools.entities.Brand;
import com.devsuperior.dstools.entities.Category;
import com.devsuperior.dstools.entities.Department;
import com.devsuperior.dstools.entities.Product;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDTO {

    private Long id;

    @Size(min = 3, max = 60, message = "Deve ter entre 3 e 60 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;
    private String description;

    @Positive(message = "Preço deve ser um valor positivo")
    private Double price;

    @Positive(message = "Quantidade deve ser um valor positivo")
    private Integer quantity;
    private String imgUrl;

    @NotEmpty(message = "Produto sem categoria não é permitido")
    private List<CategoryDTO> categories = new ArrayList<>();

    @NotEmpty(message = "Produto sem marca não é permitido")
    private List<BrandDTO> brands = new ArrayList<>();

    @NotEmpty(message = "Produto sem departamento não é permitido")
    private  List<DepartmentDTO> departments = new ArrayList<>();


    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, Integer quantity, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.quantity = entity.getQuantity();
        this.imgUrl = entity.getImgUrl();
    }

    public ProductDTO(Product entity, Set<Category> categories, Set<Department> departments, Set<Brand> brands) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
        departments.forEach(dep -> this.departments.add(new DepartmentDTO(dep)));
        brands.forEach(bra -> this.brands.add(new BrandDTO(bra)));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public List<BrandDTO> getBrands() {
        return brands;
    }

    public List<DepartmentDTO> getDepartments() {
        return departments;
    }
}
