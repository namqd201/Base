package com.tmdt.base.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "permission")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permission extends BaseEntity{
    @Column(name = "permission_name", nullable = false, length = 200)
    private String permissionName;
}
