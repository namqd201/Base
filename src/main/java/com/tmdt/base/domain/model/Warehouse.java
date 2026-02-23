package com.tmdt.base.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
public class Warehouse extends BaseEntity{
    @Column(name = "warehouse_name")
    private String warehouseName;

    private String address;
}
