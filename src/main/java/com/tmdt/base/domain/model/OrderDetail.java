package com.tmdt.base.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "orderdetail")
@Getter
@Setter
public class OrderDetail extends BaseIdEntity{

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    private Integer quantity;
    private BigDecimal price;
}
