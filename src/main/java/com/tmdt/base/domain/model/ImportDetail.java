package com.tmdt.base.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "import_detail")
public class ImportDetail extends BaseIdEntity{
    @Column(name = "import_id")
    private Long importId;

    @Column(name = "product_id")
    private Long productid;

    private Integer quantity;
    private BigDecimal price;

}
