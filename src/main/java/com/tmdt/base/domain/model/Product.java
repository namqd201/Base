package com.tmdt.base.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends BaseEntity {

    @Column(name = "product_name")
    private String productName;

    private String description;

    private BigDecimal price;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "producer_id")
    private Long producerId;

}