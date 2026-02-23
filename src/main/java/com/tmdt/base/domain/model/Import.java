package com.tmdt.base.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "import")
@Getter
@Setter
public class Import extends BaseEntity{

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "import_date")
    private LocalDateTime importDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;
}
