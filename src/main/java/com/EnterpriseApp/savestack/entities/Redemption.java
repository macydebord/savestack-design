package com.EnterpriseApp.savestack.entities;

import java.time.LocalDate;

public class Redemption {

    private Long userId;
    private Long couponId;
    private LocalDate redemptionDate;

    public Redemption(Long userId, Long couponId, LocalDate redemptionDate) {
        this.userId = userId;
        this.couponId = couponId;
        this.redemptionDate = redemptionDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public LocalDate getRedemptionDate() {
        return redemptionDate;
    }
}