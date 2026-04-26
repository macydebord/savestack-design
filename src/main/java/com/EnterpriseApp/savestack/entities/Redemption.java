package com.EnterpriseApp.savestack.entities;

import java.time.LocalDate;

/**
 * Represents a coupon redemption made by a user.
 */
public class Redemption {

    private Long userId;
    private Long couponId;
    private LocalDate redemptionDate;

    /**
     * Creates a redemption record.
     *
     * @param userId ID of the user who redeemed the coupon
     * @param couponId ID of the redeemed coupon
     * @param redemptionDate date the coupon was redeemed
     */
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