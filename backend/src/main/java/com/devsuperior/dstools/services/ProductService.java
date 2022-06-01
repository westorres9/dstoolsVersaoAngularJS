package com.devsuperior.dstools.services;

import com.devsuperior.dstools.dto.BrandDTO;
import com.devsuperior.dstools.dto.CategoryDTO;
import com.devsuperior.dstools.dto.DepartmentDTO;
import com.devsuperior.dstools.dto.ProductDTO;
import com.devsuperior.dstools.entities.Brand;
import com.devsuperior.dstools.entities.Category;
import com.devsuperior.dstools.entities.Department;
import com.devsuperior.dstools.entities.Product;
import com.devsuperior.dstools.repositories.BrandRepository;
import com.devsuperior.dstools.repositories.CategoryRepository;
import com.devsuperior.dstools.repositories.DepartmentRepository;
import com.devsuperior.dstools.repositories.ProductRepository;
import com.devsuperior.dstools.services.exceptions.DatabaseException;
import com.devsuperior.dstools.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        return page.map(x-> new ProductDTO(x, x.getCategories(),x.getDepartments(), x.getBrands()));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = productRepository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        }
        catch ( EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not Found " + id);
        }
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not Found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation " + id);
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setImgUrl(dto.getImgUrl());
        entity.getCategories().clear();
        for(CategoryDTO catDto : dto.getCategories()) {
            Category category = categoryRepository.getOne(catDto.getId());
            entity.getCategories().add(category);
        }
        entity.getDepartments().clear();
        for(DepartmentDTO depDto : dto.getDepartments()) {
            Department department = departmentRepository.getOne(depDto.getId());
            entity.getDepartments().add(department);
        }
        entity.getBrands().clear();
        for(BrandDTO braDto : dto.getBrands()) {
            Brand  brand = brandRepository.getOne(braDto.getId());
            entity.getBrands().add(brand);
        }
    }
}
