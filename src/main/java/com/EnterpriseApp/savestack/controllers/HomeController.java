package com.EnterpriseApp.savestack.controllers;

import com.EnterpriseApp.savestack.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CouponService couponService;

    public HomeController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("coupons", couponService.getActiveCoupons());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}