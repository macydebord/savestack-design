package com.EnterpriseApp.savestack.controllers;

import com.EnterpriseApp.savestack.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/allCoupons")
    public String allCoupons(Model model) {
        model.addAttribute("coupons", couponService.getAllCoupons());
        return "allCoupons";
    }

    @GetMapping("/coupon/{id}")
    public String couponDetails(@PathVariable Long id, Model model) {
        model.addAttribute("coupon", couponService.getCouponById(id));
        return "coupon";
    }

    @PostMapping("/coupon/{id}/save")
    public String saveCoupon(@PathVariable Long id) {
        couponService.saveCoupon(1L, id);
        return "redirect:/allCoupons";
    }

    @PostMapping("/coupon/{id}/redeem")
    public String redeemCoupon(@PathVariable Long id) {
        couponService.redeemCoupon(1L, id);
        return "redirect:/allCoupons";
    }
}