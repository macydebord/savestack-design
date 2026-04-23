package com.EnterpriseApp.savestack.service;

import com.EnterpriseApp.savestack.entities.Coupon;
import com.EnterpriseApp.savestack.entities.CouponStatus;
import com.EnterpriseApp.savestack.entities.Redemption;
import com.EnterpriseApp.savestack.entities.SavedCoupon;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryCouponService implements CouponService {

    private final List<Coupon> coupons = new ArrayList<>();
    private final List<SavedCoupon> savedCoupons = new ArrayList<>();
    private final List<Redemption> redemptions = new ArrayList<>();

    public InMemoryCouponService() {
        seedCoupons();
    }

    private void seedCoupons() {
        coupons.clear();
        savedCoupons.clear();
        redemptions.clear();

        coupons.add(new Coupon(1L, "Nike Sale", "NIKE20", "20% off Nike shoes and apparel", 20, LocalDate.now().plusDays(10), 10));
        coupons.add(new Coupon(2L, "Sephora Deal", "BEAUTY10", "10% off beauty products", 10, LocalDate.now().plusDays(8), 8));
        coupons.add(new Coupon(3L, "Amazon Promo", "AMZ15", "15% off select Amazon items", 15, LocalDate.now().plusDays(12), 12));
        coupons.add(new Coupon(4L, "Target Discount", "TARGET25", "25% off select Target items", 25, LocalDate.now().plusDays(14), 10));
        coupons.add(new Coupon(5L, "Ulta Beauty", "ULTA15", "15% off beauty products", 15, LocalDate.now().plusDays(6), 9));
        coupons.add(new Coupon(6L, "Bath and Body Works", "BBW20", "20% off candles and body care", 20, LocalDate.now().plusDays(7), 10));
        coupons.add(new Coupon(7L, "Starbucks Reward", "STAR5", "$5 off a Starbucks order", 5, LocalDate.now().plusDays(5), 15));
        coupons.add(new Coupon(8L, "H&M Fashion", "HM20", "20% off one clothing item", 20, LocalDate.now().plusDays(9), 7));
        coupons.add(new Coupon(9L, "Aerie Lounge", "AERIE15", "15% off lounge and basics", 15, LocalDate.now().plusDays(11), 10));
        coupons.add(new Coupon(10L, "Chipotle Meal", "CHIP10", "10% off your meal", 10, LocalDate.now().plusDays(4), 20));
        coupons.add(new Coupon(11L, "Old Navy Deal", "OLDNAVY25", "25% off select styles", 25, LocalDate.now().plusDays(10), 12));
        coupons.add(new Coupon(12L, "Best Buy Tech", "TECH15", "15% off accessories", 15, LocalDate.now().plusDays(13), 10));

        coupons.add(new Coupon(13L, "Expired Demo", "OLD5", "Example expired coupon", 5, LocalDate.now().minusDays(2), 5));

        Coupon usedUpCoupon = new Coupon(14L, "Used Up Demo", "DONE20", "Example used up coupon", 20, LocalDate.now().plusDays(3), 2);
        usedUpCoupon.setUsageCount(2);
        coupons.add(usedUpCoupon);

        updateStatuses();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        updateStatuses();
        return coupons;
    }

    @Override
    public List<Coupon> getActiveCoupons() {
        updateStatuses();
        List<Coupon> activeCoupons = new ArrayList<>();

        for (Coupon coupon : coupons) {
            if (coupon.getStatus() == CouponStatus.ACTIVE) {
                activeCoupons.add(coupon);
            }
        }

        return activeCoupons;
    }

    @Override
    public Coupon getCouponById(Long id) {
        updateStatuses();

        for (Coupon coupon : coupons) {
            if (coupon.getId().equals(id)) {
                return coupon;
            }
        }

        return null;
    }

    @Override
    public boolean saveCoupon(Long userId, Long couponId) {
        updateStatuses();

        Coupon coupon = getCouponById(couponId);

        if (coupon == null || coupon.getStatus() != CouponStatus.ACTIVE) {
            return false;
        }

        for (SavedCoupon savedCoupon : savedCoupons) {
            if (savedCoupon.getUserId().equals(userId) && savedCoupon.getCouponId().equals(couponId)) {
                return false;
            }
        }

        savedCoupons.add(new SavedCoupon(userId, couponId));
        return true;
    }

    @Override
    public boolean redeemCoupon(Long userId, Long couponId) {
        updateStatuses();

        Coupon coupon = getCouponById(couponId);

        if (coupon == null || coupon.getStatus() != CouponStatus.ACTIVE) {
            return false;
        }

        coupon.setUsageCount(coupon.getUsageCount() + 1);
        redemptions.add(new Redemption(userId, couponId, LocalDate.now()));
        updateStatuses();
        return true;
    }

    @Override
    public List<Coupon> getSavedCoupons(Long userId) {
        updateStatuses();
        List<Coupon> result = new ArrayList<>();

        for (SavedCoupon savedCoupon : savedCoupons) {
            if (savedCoupon.getUserId().equals(userId)) {
                Coupon coupon = getCouponById(savedCoupon.getCouponId());
                if (coupon != null) {
                    result.add(coupon);
                }
            }
        }

        return result;
    }

    @Override
    public List<Coupon> getRedeemedCoupons(Long userId) {
        updateStatuses();
        List<Coupon> result = new ArrayList<>();

        for (Redemption redemption : redemptions) {
            if (redemption.getUserId().equals(userId)) {
                Coupon coupon = getCouponById(redemption.getCouponId());
                if (coupon != null) {
                    result.add(coupon);
                }
            }
        }

        return result;
    }

    @Override
    public List<Coupon> searchCoupons(String keyword) {
        updateStatuses();

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllCoupons();
        }

        String lowerKeyword = keyword.toLowerCase();
        List<Coupon> results = new ArrayList<>();

        for (Coupon coupon : coupons) {
            boolean matchesTitle = coupon.getTitle().toLowerCase().contains(lowerKeyword);
            boolean matchesCode = coupon.getCode().toLowerCase().contains(lowerKeyword);
            boolean matchesDescription = coupon.getDescription().toLowerCase().contains(lowerKeyword);

            if (matchesTitle || matchesCode || matchesDescription) {
                results.add(coupon);
            }
        }

        return results;
    }

    @Override
    public void resetDemoData() {
        seedCoupons();
    }

    private void updateStatuses() {
        for (Coupon coupon : coupons) {
            if (LocalDate.now().isAfter(coupon.getExpirationDate())) {
                coupon.setStatus(CouponStatus.EXPIRED);
            } else if (coupon.getUsageCount() >= coupon.getUsageLimit()) {
                coupon.setStatus(CouponStatus.USED_UP);
            } else {
                coupon.setStatus(CouponStatus.ACTIVE);
            }
        }
    }
}