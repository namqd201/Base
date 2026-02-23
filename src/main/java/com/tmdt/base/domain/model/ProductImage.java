package com.tmdt.base.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_image")
@Getter
@Setter
public class ProductImage extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_main")
    private Boolean isMain = true;
}

