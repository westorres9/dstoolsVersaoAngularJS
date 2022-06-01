package com.devsuperior.dstools.resources;

import com.devsuperior.dstools.dto.BrandDTO;
import com.devsuperior.dstools.dto.DepartmentDTO;
import com.devsuperior.dstools.services.BrandService;
import com.devsuperior.dstools.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/brands")
public class BrandResource {

    @Autowired
    private BrandService service;

    @GetMapping
    public ResponseEntity<Page<BrandDTO>> findAllPaged(Pageable pageable) {
        Page<BrandDTO> page = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BrandDTO> findById(@PathVariable Long id) {
        BrandDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<BrandDTO> insert(@RequestBody BrandDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id, @RequestBody BrandDTO dto) {
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
