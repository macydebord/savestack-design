package com.EnterpriseApp.savestack.service;

import com.EnterpriseApp.savestack.entities.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class InMemoryCouponService implements CouponService {

    private List<Coupon> coupons = new ArrayList<>();
    private List<SavedCoupon> savedCoupons = new ArrayList<>();

    public InMemoryCouponService() {
        coupons.add(new Coupon(1L, "Nike Sale", "NIKE20", "20% off Nike", 20, LocalDate.now().plusDays(5), 5));
        coupons.add(new Coupon(2L, "Sephora Deal", "BEAUTY10", "10% off beauty", 10, LocalDate.now().minusDays(1), 10));
        coupons.add(new Coupon(3L, "Amazon Promo", "AMZ15", "15% off Amazon", 15, LocalDate.now().plusDays(2), 1));
    }

    @Override
    public List<Coupon> getAllCoupons() {
        updateStatuses();
        return coupons;
    }

    @Override
    public List<Coupon> getActiveCoupons() {
        updateStatuses();
        List<Coupon> active = new ArrayList<>();
        for (Coupon c : coupons) {
            if (c.getStatus() == CouponStatus.ACTIVE) {
                active.add(c);
            }
        }
        return active;
    }

    @Override
    public Coupon getCouponById(Long id) {
        return coupons.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean saveCoupon(Long userId, Long couponId) {
        for (SavedCoupon sc : savedCoupons) {
            if (sc.getUserId().equals(userId) && sc.getCouponId().equals(couponId)) {
                return false;
            }
        }

        Coupon c = getCouponById(couponId);
        if (c == null || c.getStatus() != CouponStatus.ACTIVE) return false;

        savedCoupons.add(new SavedCoupon(userId, couponId));
        return true;
    }

    @Override
    public boolean redeemCoupon(Long userId, Long couponId) {
        Coupon c = getCouponById(couponId);
        if (c == null) return false;

        updateStatuses();

        if (c.getStatus() != CouponStatus.ACTIVE) return false;

        c.setUsageCount(c.getUsageCount() + 1);
        updateStatuses();
        return true;
    }

    private void updateStatuses() {
        for (Coupon c : coupons) {
            if (LocalDate.now().isAfter(c.getExpirationDate())) {
                c.setStatus(CouponStatus.EXPIRED);
            } else if (c.getUsageCount() >= c.getUsageLimit()) {
                c.setStatus(CouponStatus.USED_UP);
            } else {
                c.setStatus(CouponStatus.ACTIVE);
            }
        }
    }
}