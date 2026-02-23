package com.tmdt.base.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "feedback")
@Getter
@Setter
public class Feedback extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    private String content;
}
