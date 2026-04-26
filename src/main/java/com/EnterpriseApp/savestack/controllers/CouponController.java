package com.EnterpriseApp.savestack.controllers;

import com.EnterpriseApp.savestack.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles web page routes for coupon features such as viewing, saving, and redeeming coupons.
 */
@Controller
public class CouponController {

    private final CouponService couponService;

    /**
     * Creates a CouponController with a coupon service dependency.
     *
     * @param couponService service used to access coupon business logic
     */
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * Displays the dashboard page with coupon counts and user coupon activity.
     *
     * @param model stores data sent to the dashboard view
     * @return dashboard template
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("availableCount", couponService.getActiveCoupons().size());
        model.addAttribute("savedCount", couponService.getSavedCoupons(1L).size());
        model.addAttribute("redeemedCount", couponService.getRedeemedCoupons(1L).size());
        model.addAttribute("savedCoupons", couponService.getSavedCoupons(1L));
        model.addAttribute("redeemedCoupons", couponService.getRedeemedCoupons(1L));
        return "dashboard";
    }

    /**
     * Displays all coupons and optionally filters coupons by search keyword.
     *
     * @param keyword optional search keyword
     * @param model stores coupon data sent to the all coupons view
     * @return allCoupons template
     */
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

    /**
     * Displays details for one coupon.
     *
     * @param id coupon ID from the URL
     * @param model stores coupon data sent to the coupon details view
     * @return coupon template
     */
    @GetMapping("/coupon/{id}")
    public String couponDetails(@PathVariable Long id, Model model) {
        model.addAttribute("coupon", couponService.getCouponById(id));
        return "coupon";
    }

    /**
     * Saves a coupon for the demo user.
     *
     * @param id coupon ID from the URL
     * @param redirectAttributes stores a temporary success or error message
     * @return redirect to all coupons page
     */
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

    /**
     * Redeems a coupon for the demo user.
     *
     * @param id coupon ID from the URL
     * @param redirectAttributes stores a temporary success or error message
     * @return redirect to all coupons page
     */
    @PostMapping("/coupon/{id}/redeem")
    public String redeemCoupon(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean wasRedeemed = couponService.redeemCoupon(1L, id);

        if (wasRedeemed) {
            redirectAttributes.addFlashAttribute("message", "Coupon redeemed successfully.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Coupon could not be redeemed. It may need to be saved first, expired, or already used up.");
        }

        return "redirect:/allCoupons";
    }

    /**
     * Displays coupons saved by the demo user.
     *
     * @param model stores saved coupon data
     * @return savedCoupons template
     */
    @GetMapping("/savedCoupons")
    public String savedCoupons(Model model) {
        model.addAttribute("coupons", couponService.getSavedCoupons(1L));
        return "savedCoupons";
    }

    /**
     * Displays coupons redeemed by the demo user.
     *
     * @param model stores redeemed coupon data
     * @return redeemedCoupons template
     */
    @GetMapping("/redeemedCoupons")
    public String redeemedCoupons(Model model) {
        model.addAttribute("coupons", couponService.getRedeemedCoupons(1L));
        return "redeemedCoupons";
    }

    /**
     * Resets the demo data back to its original state.
     *
     * @param redirectAttributes stores a temporary confirmation message
     * @return redirect to dashboard page
     */
    @PostMapping("/resetDemo")
    public String resetDemo(RedirectAttributes redirectAttributes) {
        couponService.resetDemoData();
        redirectAttributes.addFlashAttribute("message", "Demo data has been reset.");
        return "redirect:/dashboard";
    }
}