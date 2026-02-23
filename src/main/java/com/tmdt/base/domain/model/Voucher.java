package com.tmdt.base.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "voucher")
@Getter
@Setter
public class Voucher extends BaseEntity{
    private String code;
    private BigDecimal discount;
}
