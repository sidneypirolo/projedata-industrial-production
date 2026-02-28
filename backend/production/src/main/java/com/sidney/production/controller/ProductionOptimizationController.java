package com.sidney.production.controller;

import com.sidney.production.dto.ProductionOptimizationResponsedto;
import com.sidney.production.service.ProductionOptimizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductionOptimizationController {

    private final ProductionOptimizationService optimizationService;

    public ProductionOptimizationController(ProductionOptimizationService optimizationService) {
        this.optimizationService = optimizationService;
    }

    @GetMapping("/production-optimization")
    public ProductionOptimizationResponsedto optimize() {
        return optimizationService.optimizeProduction();
    }
}