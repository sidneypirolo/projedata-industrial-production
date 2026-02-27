package com.sidney.production.dto;

public class ProductionPlanningResponsedto {

     private Long productId;
     private String productName;
     private int maxProduction; 
     private String limitingRawMaterial;

     public ProductionPlanningResponsedto(
               Long productId,
               String productName,
               int maxProduction,
               String limitingRawMaterial) {
          this.productId = productId;
          this.productName = productName;
          this.maxProduction = maxProduction;
          this.limitingRawMaterial = limitingRawMaterial;
     }

     public Long getProductId() { return productId; }
     public String getProductName() { return productName; }
     public int getMaxProduction() { return maxProduction; }
     public String getLimitingRawMaterial() { return limitingRawMaterial; }
}