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

    // 🔹 Listar todos
    @GetMapping
    public List<RawMaterial> findAll() {
        return repository.findAll();
    }

    // 🔹 Buscar por ID
    @GetMapping("/{id}")
    public RawMaterial findById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // 🔹 Criar novo
    @PostMapping
    public RawMaterial create(@RequestBody RawMaterial rawMaterial) {
        return repository.save(rawMaterial);
    }
}