package com.sidney.production.service;

import com.sidney.production.dto.ProductionPlanningResponsedto;
import com.sidney.production.entity.Product;
import com.sidney.production.entity.ProductMaterial;
import com.sidney.production.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductionPlanningService {

    private final ProductRepository productRepository;

    public ProductionPlanningService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductionPlanningResponsedto calculateMaxProduction(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getComposition() == null || product.getComposition().isEmpty()) {
            throw new RuntimeException("Product has no materials associated");
        }

        int maxProduction = Integer.MAX_VALUE;
        String limitingMaterial = null;

        for (ProductMaterial material : product.getComposition()) {

            if (material.getRawMaterial() == null) {
                throw new RuntimeException(
                        "Raw material not found for product material ID: " + material.getId()
                );
            }

            double available = material.getRawMaterial().getQuantityInStock();
            double required = material.getQuantityRequired();

            if (required <= 0) {
                continue;
            }

            int possibleProduction = (int) (available / required);

            if (possibleProduction < maxProduction) {
                maxProduction = possibleProduction;
                limitingMaterial = material.getRawMaterial().getName();
            }
        }

        if (maxProduction == Integer.MAX_VALUE) {
            maxProduction = 0;
        }

        return new ProductionPlanningResponsedto(
                product.getId(),
                product.getName(),
                maxProduction,
                limitingMaterial
        );
    }
}