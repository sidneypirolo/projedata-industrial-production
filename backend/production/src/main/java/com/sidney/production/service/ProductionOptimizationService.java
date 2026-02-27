package com.sidney.production.service;

import com.sidney.production.dto.*;
import com.sidney.production.entity.*;
import com.sidney.production.repository.ProductRepository;
import org.springframework.stereotype.Service;
    
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductionOptimizationService {

    private final ProductRepository productRepository;

    public ProductionOptimizationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductionOptimizationResponseDTO optimizeProduction() {

        List<Product> products = productRepository.findAll();

        // Ordena por maior valor de venda 
        products.sort(Comparator.comparing(Product::getPrice).reversed());

        Map<Long, Double> simulatedStock = new HashMap<>();

        BigDecimal totalRevenue = BigDecimal.ZERO;

        List<ProductionPlanItemDTO> planItems = new ArrayList<>();

        for (Product product : products) {

            if (product.getComposition() == null || product.getComposition().isEmpty())
                continue;

            int maxProduction = Integer.MAX_VALUE;

            for (ProductMaterial material : product.getComposition()) {

                RawMaterial raw = material.getRawMaterial();

                if (raw == null) continue;

                double availableStock =
                        simulatedStock.getOrDefault(raw.getId(),
                                raw.getQuantityInStock());

                double required = material.getQuantityRequired();

                if (required <= 0) continue;

                int possibleProduction = (int) (availableStock / required);

                maxProduction = Math.min(maxProduction, possibleProduction);
            }

            if (maxProduction > 0 && maxProduction != Integer.MAX_VALUE) {

                BigDecimal revenue =
                        product.getPrice()
                                .multiply(BigDecimal.valueOf(maxProduction));

                totalRevenue = totalRevenue.add(revenue);

                planItems.add(
                        new ProductionPlanItemDTO(
                                product.getName(),
                                maxProduction,
                                revenue
                        )
                );
            }
        }

        return new ProductionOptimizationResponseDTO(
                totalRevenue,
                planItems
        );
    }
}