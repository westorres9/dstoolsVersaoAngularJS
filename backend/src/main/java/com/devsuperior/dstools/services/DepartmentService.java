package com.devsuperior.dstools.services;

import com.devsuperior.dstools.dto.BrandDTO;
import com.devsuperior.dstools.dto.DepartmentDTO;
import com.devsuperior.dstools.entities.Brand;
import com.devsuperior.dstools.entities.Department;
import com.devsuperior.dstools.repositories.CategoryRepository;
import com.devsuperior.dstools.repositories.DepartmentRepository;
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
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;



    @Transactional(readOnly = true)
    public Page<DepartmentDTO> findAllPaged(Pageable pageable) {
        Page<Department> page = repository.findAll(pageable);
        return page.map(x -> new DepartmentDTO(x));
    }

    @Transactional(readOnly = true)
    public DepartmentDTO findById(Long id) {
        Optional<Department> obj = repository.findById(id);
        Department entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
        return new DepartmentDTO(entity);
    }

    @Transactional
    public DepartmentDTO insert(DepartmentDTO dto) {
        Department entity = new Department();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new DepartmentDTO(entity);
    }

    @Transactional
    public DepartmentDTO update(Long id, DepartmentDTO dto) {
        try {
            Department entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new DepartmentDTO(entity);
        }
        catch ( EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not Found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not Found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation " + id);
        }
    }
}
