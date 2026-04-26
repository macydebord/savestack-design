package com.EnterpriseApp.savestack.service;

import com.EnterpriseApp.savestack.entities.Coupon;

import java.util.List;

/**
 * Defines the main business operations for managing coupons in SaveStack.
 */
public interface CouponService {

    /**
     * Gets all coupons in the system.
     *
     * @return list of all coupons
     */
    List<Coupon> getAllCoupons();

    /**
     * Gets only coupons that are currently active.
     *
     * @return list of active coupons
     */
    List<Coupon> getActiveCoupons();

    /**
     * Finds a coupon by its ID.
     *
     * @param id coupon ID
     * @return matching coupon, or null if not found
     */
    Coupon getCouponById(Long id);

    /**
     * Saves a coupon for a user.
     *
     * @param userId ID of the user saving the coupon
     * @param couponId ID of the coupon being saved
     * @return true if saved successfully, false otherwise
     */
    boolean saveCoupon(Long userId, Long couponId);

    /**
     * Redeems a coupon for a user.
     *
     * @param userId ID of the user redeeming the coupon
     * @param couponId ID of the coupon being redeemed
     * @return true if redeemed successfully, false otherwise
     */
    boolean redeemCoupon(Long userId, Long couponId);

    /**
     * Gets all coupons saved by a user.
     *
     * @param userId ID of the user
     * @return list of saved coupons
     */
    List<Coupon> getSavedCoupons(Long userId);

    /**
     * Gets all coupons redeemed by a user.
     *
     * @param userId ID of the user
     * @return list of redeemed coupons
     */
    List<Coupon> getRedeemedCoupons(Long userId);

    /**
     * Searches coupons by title, code, or description.
     *
     * @param keyword search text entered by the user
     * @return list of matching coupons
     */
    List<Coupon> searchCoupons(String keyword);

    /**
     * Resets the app back to its original demo coupon data.
     */
    void resetDemoData();
}