package com.devsuperior.dstools.resources;

import com.devsuperior.dstools.dto.DepartmentDTO;
import com.devsuperior.dstools.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentResource {

    @Autowired
    private DepartmentService service;

    @GetMapping
    public ResponseEntity<Page<DepartmentDTO>> findAllPaged(Pageable pageable) {
        Page<DepartmentDTO> page = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> findById(@PathVariable Long id) {
        DepartmentDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> insert(@RequestBody DepartmentDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @RequestBody DepartmentDTO dto) {
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
