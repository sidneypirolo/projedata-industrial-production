package com.sidney.production.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductionOptimizationResponseDTO {

    private BigDecimal totalEstimatedRevenue;
    private List<ProductionPlanItemDTO> productionPlan;

    public ProductionOptimizationResponseDTO(BigDecimal totalEstimatedRevenue,
                                             List<ProductionPlanItemDTO> productionPlan) {
        this.totalEstimatedRevenue = totalEstimatedRevenue;
        this.productionPlan = productionPlan;
    }

    public BigDecimal getTotalEstimatedRevenue() {
        return totalEstimatedRevenue;
    }

    public List<ProductionPlanItemDTO> getProductionPlan() {
        return productionPlan;
    }
}