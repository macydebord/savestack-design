package com.savestack.model;

import java.time.LocalDate;

public class Coupon {

    private int couponId;
    private int discountPercent;
    private int usageLimit;
    private int usageCount;
    private String code;
    private LocalDate expirationDate;
    private CouponStatus status;

    public void updateStatus() {
        if (expirationDate != null && expirationDate.isBefore(LocalDate.now())) {
            this.status = CouponStatus.EXPIRED;
        } else if (usageCount >= usageLimit) {
            this.status = CouponStatus.USED_UP;
        }else {
            this.status = CouponStatus.ACTIVE;
        }
    }
}
