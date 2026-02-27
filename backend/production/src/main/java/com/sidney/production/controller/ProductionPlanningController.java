package com.sidney.production.controller;

import com.sidney.production.dto.ProductionPlanningResponsedto;
import com.sidney.production.service.ProductionPlanningService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/production-planning")
public class ProductionPlanningController {

     private final ProductionPlanningService productionPlanningService;

     public ProductionPlanningController(ProductionPlanningService productionPlanningService) {
          this.productionPlanningService = productionPlanningService;
     }

     @GetMapping("/{productId}")
     public ProductionPlanningResponsedto calculate(@PathVariable Long productId) {
          return productionPlanningService.calculateMaxProduction(productId);
     }
}