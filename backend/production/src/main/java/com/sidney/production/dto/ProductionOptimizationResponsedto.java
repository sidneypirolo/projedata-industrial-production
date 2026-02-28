package com.sidney.production.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductionOptimizationResponsedto {

    private BigDecimal totalEstimatedRevenue;
    private List<ProductionPlanItemdto> productionPlan;

    public ProductionOptimizationResponsedto(BigDecimal totalEstimatedRevenue,
                                             List<ProductionPlanItemdto> productionPlan) {
        this.totalEstimatedRevenue = totalEstimatedRevenue;
        this.productionPlan = productionPlan;
    }

    public BigDecimal getTotalEstimatedRevenue() {
        return totalEstimatedRevenue;
    }

    public List<ProductionPlanItemdto> getProductionPlan() {
        return productionPlan;
    }
}