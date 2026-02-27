package com.sidney.production.entity;

import javax.annotation.processing.Generated;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private BigDecimal price;

    @OneToMany(mappedBy= "product", fetch= FetchType.EAGER , cascade= CascadeType.ALL,orphanRemoval=true )
    private List<ProductMaterial>composition;
    
    //getters and setters
    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
         this.name = name;
    }

    public BigDecimal getPrice() {
         return price;
    }

    public void setPrice(BigDecimal price) {
         this.price = price;
    }

    public List<ProductMaterial> getComposition() {
        return composition;
    }

    public void setComposition(List<ProductMaterial> composition) {
         this.composition = composition;
     }
}
