package com.sidney.production.service;

import com.sidney.production.dto.ProductionOptimizationResponseDTO;
import com.sidney.production.entity.*;
import com.sidney.production.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductionOptimizationServiceTest {

    private ProductRepository productRepository;
    private ProductionOptimizationService service;

    @BeforeEach
    void setup() {
        productRepository = Mockito.mock(ProductRepository.class);
        service = new ProductionOptimizationService(productRepository);
    }

    @Test
    void shouldPrioritizeHigherValueProduct() {

        // Raw Material
        RawMaterial wood = new RawMaterial();
        wood.setId(1L);
        wood.setName("Wood");
        wood.setQuantityInStock(100);

        // Product 1 (Expensive)
        Product table = new Product();
        table.setId(1L);
        table.setName("Table");
        table.setPrice(BigDecimal.valueOf(500));

        ProductMaterial tableMaterial = new ProductMaterial();
        tableMaterial.setRawMaterial(wood);
        tableMaterial.setQuantityRequired(10);

        table.setComposition(List.of(tableMaterial));

        // Product 2 (Cheaper)
        Product chair = new Product();
        chair.setId(2L);
        chair.setName("Chair");
        chair.setPrice(BigDecimal.valueOf(100));

        ProductMaterial chairMaterial = new ProductMaterial();
        chairMaterial.setRawMaterial(wood);
        chairMaterial.setQuantityRequired(5);

        chair.setComposition(List.of(chairMaterial));

        when(productRepository.findAll()).thenReturn(List.of(table, chair));

        ProductionOptimizationResponseDTO response =
                service.optimizeProduction();

        assertEquals(BigDecimal.valueOf(5000), response.getTotalEstimatedRevenue());
        assertEquals(1, response.getProductionPlan().size());
        assertEquals("Table", response.getProductionPlan().get(0).getProductName());
        assertEquals(10, response.getProductionPlan().get(0).getQuantity());
    }

    @Test
    void shouldReturnZeroWhenNoStock() {

        RawMaterial wood = new RawMaterial();
        wood.setId(1L);
        wood.setName("Wood");
        wood.setQuantityInStock(0);

        Product table = new Product();
        table.setId(1L);
        table.setName("Table");
        table.setPrice(BigDecimal.valueOf(500));

        ProductMaterial tableMaterial = new ProductMaterial();
        tableMaterial.setRawMaterial(wood);
        tableMaterial.setQuantityRequired(10);

        table.setComposition(List.of(tableMaterial));

        when(productRepository.findAll()).thenReturn(List.of(table));

        ProductionOptimizationResponseDTO response =
                service.optimizeProduction();

        assertEquals(BigDecimal.ZERO, response.getTotalEstimatedRevenue());
        assertTrue(response.getProductionPlan().isEmpty());
    }
}