package com.EnterpriseApp.savestack.controllers;

import com.EnterpriseApp.savestack.entities.Coupon;
import com.EnterpriseApp.savestack.service.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class ApiCouponController {

    private final CouponService couponService;

    public ApiCouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public List<Coupon> getAll() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/active")
    public List<Coupon> getActive() {
        return couponService.getActiveCoupons();
    }
}