package com.EnterpriseApp.savestack.entities;

public class SavedCoupon {

    private Long userId;
    private Long couponId;

    public SavedCoupon(Long userId, Long couponId) {
        this.userId = userId;
        this.couponId = couponId;
    }

    public Long getUserId() { return userId; }
    public Long getCouponId() { return couponId; }
}