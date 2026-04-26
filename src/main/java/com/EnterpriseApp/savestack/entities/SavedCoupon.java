package com.EnterpriseApp.savestack.entities;

/**
 * Represents a relationship between a user and a coupon they saved.
 */
public class SavedCoupon {

    private Long userId;
    private Long couponId;

    /**
     * Creates a saved coupon record.
     *
     * @param userId ID of the user who saved the coupon
     * @param couponId ID of the saved coupon
     */
    public SavedCoupon(Long userId, Long couponId) {
        this.userId = userId;
        this.couponId = couponId;
    }

    public Long getUserId() { return userId; }

    public Long getCouponId() { return couponId; }
}