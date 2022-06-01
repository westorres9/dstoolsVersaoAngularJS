package com.devsuperior.dstools.services;

import com.devsuperior.dstools.dto.BrandDTO;
import com.devsuperior.dstools.dto.DepartmentDTO;
import com.devsuperior.dstools.entities.Brand;
import com.devsuperior.dstools.entities.Department;
import com.devsuperior.dstools.repositories.BrandRepository;
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
public class BrandService {

    @Autowired
    private BrandRepository repository;

    @Transactional(readOnly = true)
    public Page<BrandDTO> findAllPaged(Pageable pageable) {
        Page<Brand> page = repository.findAll(pageable);
        return page.map(x -> new BrandDTO(x));
    }

    @Transactional(readOnly = true)
    public BrandDTO findById(Long id) {
        Optional<Brand> obj = repository.findById(id);
        Brand entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
        return new BrandDTO(entity);
    }

    @Transactional
    public BrandDTO insert(BrandDTO dto) {
        Brand entity = new Brand();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new BrandDTO(entity);
    }

    @Transactional
    public BrandDTO update(Long id, BrandDTO dto) {
        try {
            Brand entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new BrandDTO(entity);
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
