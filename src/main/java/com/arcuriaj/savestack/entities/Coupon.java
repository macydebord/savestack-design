package com.arcuriaj.savestack.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "coupons")
public class Coupon {
     @Id
    private Long id;

    private String name;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @Column(name = "expire_date")
    private LocalDateTime expiryDate;

    @Column (name = "usage_Limit")
    private Integer usageLimit;

    @Column (name = "times_used")
    private int timesUsed;

}
