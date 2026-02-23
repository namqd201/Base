package com.tmdt.base.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "product_warehouse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductWarehouse extends BaseIdEntity {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "quantity")
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductWarehouse)) return false;
        ProductWarehouse that = (ProductWarehouse) o;
        return Objects.equals(productId, that.productId)
                && Objects.equals(warehouseId, that.warehouseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, warehouseId);
    }
}

