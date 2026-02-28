package com.sidney.production.dto;

import java.math.BigDecimal;

public class ProductionPlanItemdto {

    private String productName;
    private int quantity;
    private BigDecimal estimatedRevenue;

    public ProductionPlanItemdto(String productName,
                                  int quantity,
                                  BigDecimal estimatedRevenue) {
        this.productName = productName;
        this.quantity = quantity;
        this.estimatedRevenue = estimatedRevenue;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getEstimatedRevenue() {
        return estimatedRevenue;
    }
}