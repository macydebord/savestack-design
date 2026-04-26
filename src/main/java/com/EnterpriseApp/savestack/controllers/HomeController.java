package com.EnterpriseApp.savestack.controllers;

import com.EnterpriseApp.savestack.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password) {

        String testEmail = "mail@gmail.com";
        String testPassword = "pass";

        if (email.equals(testEmail) && password.equals(testPassword)) {
            return "redirect:/";
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}