package com.EnterpriseApp.savestack.controllers;

import com.EnterpriseApp.savestack.entities.Coupon;
import com.EnterpriseApp.savestack.service.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that exposes coupon data as JSON.
 */
@RestController
@RequestMapping("/api/coupons")
public class ApiCouponController {

    private final CouponService couponService;

    /**
     * Creates an API controller with a coupon service dependency.
     *
     * @param couponService service used to retrieve coupon data
     */
    public ApiCouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * Returns all coupons as JSON.
     *
     * @return list of all coupons
     */
    @GetMapping
    public List<Coupon> getAll() {
        return couponService.getAllCoupons();
    }

    /**
     * Returns active coupons as JSON.
     *
     * @return list of active coupons
     */
    @GetMapping("/active")
    public List<Coupon> getActive() {
        return couponService.getActiveCoupons();
    }
}