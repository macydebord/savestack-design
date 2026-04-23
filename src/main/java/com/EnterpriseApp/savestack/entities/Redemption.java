package com.EnterpriseApp.savestack.entities;

public class Redemption {

    private Long userId;
    private Long couponId;

    public Redemption(Long userId, Long couponId) {
        this.userId = userId;
        this.couponId = couponId;
    }
}