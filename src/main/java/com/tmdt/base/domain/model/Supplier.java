package com.tmdt.base.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class Supplier extends BaseEntity {

    @Column(name = "supplier_name")
    private String supplierName;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
}
