package com.EnterpriseApp.savestack.controllers;

import com.EnterpriseApp.savestack.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("availableCount", couponService.getActiveCoupons().size());
        model.addAttribute("savedCount", couponService.getSavedCoupons(1L).size());
        model.addAttribute("redeemedCount", couponService.getRedeemedCoupons(1L).size());
        model.addAttribute("savedCoupons", couponService.getSavedCoupons(1L));
        model.addAttribute("redeemedCoupons", couponService.getRedeemedCoupons(1L));
        return "dashboard";
    }

    @GetMapping("/allCoupons")
    public String allCoupons(@RequestParam(required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("coupons", couponService.searchCoupons(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("coupons", couponService.getAllCoupons());
            model.addAttribute("keyword", "");
        }

        return "allCoupons";
    }

    @GetMapping("/coupon/{id}")
    public String couponDetails(@PathVariable Long id, Model model) {
        model.addAttribute("coupon", couponService.getCouponById(id));
        return "coupon";
    }

    @PostMapping("/coupon/{id}/save")
    public String saveCoupon(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean wasSaved = couponService.saveCoupon(1L, id);

        if (wasSaved) {
            redirectAttributes.addFlashAttribute("message", "Coupon saved successfully.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Coupon could not be saved. It may already be saved or no longer active.");
        }

        return "redirect:/allCoupons";
    }

    @PostMapping("/coupon/{id}/redeem")
    public String redeemCoupon(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean wasRedeemed = couponService.redeemCoupon(1L, id);

        if (wasRedeemed) {
            redirectAttributes.addFlashAttribute("message", "Coupon redeemed successfully.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Coupon could not be redeemed. It may be expired or already used up.");
        }

        return "redirect:/allCoupons";
    }

    @GetMapping("/savedCoupons")
    public String savedCoupons(Model model) {
        model.addAttribute("coupons", couponService.getSavedCoupons(1L));
        return "savedCoupons";
    }

    @GetMapping("/redeemedCoupons")
    public String redeemedCoupons(Model model) {
        model.addAttribute("coupons", couponService.getRedeemedCoupons(1L));
        return "redeemedCoupons";
    }

    @PostMapping("/resetDemo")
    public String resetDemo(RedirectAttributes redirectAttributes) {
        couponService.resetDemoData();
        redirectAttributes.addFlashAttribute("message", "Demo data has been reset.");
        return "redirect:/dashboard";
    }
}