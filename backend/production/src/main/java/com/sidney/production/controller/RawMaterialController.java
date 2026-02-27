package com.sidney.production.controller;

import com.sidney.production.entity.RawMaterial;
import com.sidney.production.repository.RawMaterialRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raw-materials")
public class RawMaterialController {

    private final RawMaterialRepository repository;

    public RawMaterialController(RawMaterialRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RawMaterial> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public RawMaterial findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw material not found"));
    }

    @PostMapping
    public RawMaterial create(@RequestBody RawMaterial rawMaterial) {
        return repository.save(rawMaterial);
    }

    @PutMapping("/{id}")
    public RawMaterial update(@PathVariable Long id,
                              @RequestBody RawMaterial updated) {

        RawMaterial existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw material not found"));

        existing.setName(updated.getName());
        existing.setQuantityInStock(updated.getQuantityInStock());
        existing.setUnit(updated.getUnit());

        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}