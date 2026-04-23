package com.EnterpriseApp.savestack.service;

import com.EnterpriseApp.savestack.entities.Coupon;
import java.util.List;

public interface CouponService {

    List<Coupon> getAllCoupons();
    List<Coupon> getActiveCoupons();
    Coupon getCouponById(Long id);

    boolean saveCoupon(Long userId, Long couponId);
    boolean redeemCoupon(Long userId, Long couponId);
}