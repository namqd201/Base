package com.tmdt.base.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producer")
@Getter
@Setter
public class Producer extends BaseEntity{
    @Column(name = "producer_name")
    private String producerName;
}
