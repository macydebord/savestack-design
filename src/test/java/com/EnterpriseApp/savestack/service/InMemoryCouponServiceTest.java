package com.EnterpriseApp.savestack.service;

import com.EnterpriseApp.savestack.entities.Coupon;
import com.EnterpriseApp.savestack.entities.CouponStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InMemoryCouponService.
 * These tests follow a Given/When/Then style.
 */
class InMemoryCouponServiceTest {

    private InMemoryCouponService couponService;

    @BeforeEach
    void setUp() {
        couponService = new InMemoryCouponService();
    }

    @Test
    void givenDemoData_whenGetAllCoupons_thenReturnsCoupons() {
        // Given
        // The service has been initialized with demo coupon data.

        // When
        List<Coupon> coupons = couponService.getAllCoupons();

        // Then
        assertFalse(coupons.isEmpty());
        assertEquals(14, coupons.size());
    }

    @Test
    void givenActiveCoupons_whenGetActiveCoupons_thenOnlyActiveCouponsReturned() {
        // Given
        // Demo data includes active, expired, and used up coupons.

        // When
        List<Coupon> activeCoupons = couponService.getActiveCoupons();

        // Then
        assertFalse(activeCoupons.isEmpty());

        for (Coupon coupon : activeCoupons) {
            assertEquals(CouponStatus.ACTIVE, coupon.getStatus());
        }
    }

    @Test
    void givenValidCouponId_whenGetCouponById_thenReturnsCorrectCoupon() {
        // Given
        Long couponId = 1L;

        // When
        Coupon coupon = couponService.getCouponById(couponId);

        // Then
        assertNotNull(coupon);
        assertEquals(couponId, coupon.getId());
        assertEquals("Nike Sale", coupon.getTitle());
    }

    @Test
    void givenInvalidCouponId_whenGetCouponById_thenReturnsNull() {
        // Given
        Long invalidCouponId = 999L;

        // When
        Coupon coupon = couponService.getCouponById(invalidCouponId);

        // Then
        assertNull(coupon);
    }

    @Test
    void givenActiveCoupon_whenSaveCoupon_thenCouponAppearsInSavedCoupons() {
        // Given
        Long userId = 1L;
        Long couponId = 1L;

        // When
        boolean result = couponService.saveCoupon(userId, couponId);
        List<Coupon> savedCoupons = couponService.getSavedCoupons(userId);

        // Then
        assertTrue(result);
        assertEquals(1, savedCoupons.size());
        assertEquals(couponId, savedCoupons.get(0).getId());
    }

    @Test
    void givenAlreadySavedCoupon_whenSaveCouponAgain_thenSaveFails() {
        // Given
        Long userId = 1L;
        Long couponId = 1L;
        couponService.saveCoupon(userId, couponId);

        // When
        boolean result = couponService.saveCoupon(userId, couponId);

        // Then
        assertFalse(result);
        assertEquals(1, couponService.getSavedCoupons(userId).size());
    }

    @Test
    void givenExpiredCoupon_whenSaveCoupon_thenSaveFails() {
        // Given
        Long userId = 1L;
        Long expiredCouponId = 13L;

        // When
        boolean result = couponService.saveCoupon(userId, expiredCouponId);

        // Then
        assertFalse(result);
    }

    @Test
    void givenUsedUpCoupon_whenSaveCoupon_thenSaveFails() {
        // Given
        Long userId = 1L;
        Long usedUpCouponId = 14L;

        // When
        boolean result = couponService.saveCoupon(userId, usedUpCouponId);

        // Then
        assertFalse(result);
    }

    @Test
    void givenSavedActiveCoupon_whenRedeemCoupon_thenCouponAppearsInRedeemedCoupons() {
        // Given
        Long userId = 1L;
        Long couponId = 1L;
        couponService.saveCoupon(userId, couponId);

        // When
        boolean result = couponService.redeemCoupon(userId, couponId);
        List<Coupon> redeemedCoupons = couponService.getRedeemedCoupons(userId);

        // Then
        assertTrue(result);
        assertEquals(1, redeemedCoupons.size());
        assertEquals(couponId, redeemedCoupons.get(0).getId());
    }

    @Test
    void givenUnsavedCoupon_whenRedeemCoupon_thenRedeemFails() {
        // Given
        Long userId = 1L;
        Long couponId = 1L;

        // When
        boolean result = couponService.redeemCoupon(userId, couponId);

        // Then
        assertFalse(result);
        assertTrue(couponService.getRedeemedCoupons(userId).isEmpty());
    }

    @Test
    void givenExpiredCoupon_whenRedeemCoupon_thenRedeemFails() {
        // Given
        Long userId = 1L;
        Long expiredCouponId = 13L;

        // When
        boolean result = couponService.redeemCoupon(userId, expiredCouponId);

        // Then
        assertFalse(result);
    }

    @Test
    void givenUsedUpCoupon_whenRedeemCoupon_thenRedeemFails() {
        // Given
        Long userId = 1L;
        Long usedUpCouponId = 14L;

        // When
        boolean result = couponService.redeemCoupon(userId, usedUpCouponId);

        // Then
        assertFalse(result);
    }

    @Test
    void givenSearchKeywordMatchingTitle_whenSearchCoupons_thenMatchingCouponReturned() {
        // Given
        String keyword = "Nike";

        // When
        List<Coupon> results = couponService.searchCoupons(keyword);

        // Then
        assertFalse(results.isEmpty());
        assertEquals("Nike Sale", results.get(0).getTitle());
    }

    @Test
    void givenSearchKeywordMatchingCode_whenSearchCoupons_thenMatchingCouponReturned() {
        // Given
        String keyword = "AMZ15";

        // When
        List<Coupon> results = couponService.searchCoupons(keyword);

        // Then
        assertFalse(results.isEmpty());
        assertEquals("Amazon Promo", results.get(0).getTitle());
    }

    @Test
    void givenSearchKeywordMatchingDescription_whenSearchCoupons_thenMatchingCouponReturned() {
        // Given
        String keyword = "beauty";

        // When
        List<Coupon> results = couponService.searchCoupons(keyword);

        // Then
        assertFalse(results.isEmpty());
    }

    @Test
    void givenBlankSearchKeyword_whenSearchCoupons_thenAllCouponsReturned() {
        // Given
        String keyword = "";

        // When
        List<Coupon> results = couponService.searchCoupons(keyword);

        // Then
        assertEquals(couponService.getAllCoupons().size(), results.size());
    }

    @Test
    void givenDemoDataChanged_whenResetDemoData_thenSavedAndRedeemedCouponsAreCleared() {
        // Given
        Long userId = 1L;
        Long couponId = 1L;
        couponService.saveCoupon(userId, couponId);
        couponService.redeemCoupon(userId, couponId);

        // When
        couponService.resetDemoData();

        // Then
        assertTrue(couponService.getSavedCoupons(userId).isEmpty());
        assertTrue(couponService.getRedeemedCoupons(userId).isEmpty());
        assertEquals(14, couponService.getAllCoupons().size());
    }
}