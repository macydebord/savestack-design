package com.arcuriaj.savestack.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "saved_coupon")
public class SavedCoupon {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    private boolean isRedeem;

}
