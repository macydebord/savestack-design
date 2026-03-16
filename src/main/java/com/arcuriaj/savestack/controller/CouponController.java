package com.arcuriaj.savestack.controller;

import com.arcuriaj.savestack.model.Coupon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class CouponController {

    @GetMapping("/api/coupons")
    public List<Coupon> getCoupons() {
        return Collections.emptyList();
    }
}
