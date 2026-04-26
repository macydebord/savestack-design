package com.EnterpriseApp.savestack.controllers;

import com.EnterpriseApp.savestack.service.CouponService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles general page routes such as the home page, login page, and signup page.
 */
@Controller
public class HomeController {

    private final CouponService couponService;

    /**
     * Creates a HomeController with a coupon service dependency.
     *
     * @param couponService service used to retrieve coupon data for the home page
     */
    public HomeController(CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * Displays the home page with active coupons.
     *
     * @param model stores coupon data sent to the home view
     * @return index template
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("coupons", couponService.getActiveCoupons());
        return "index";
    }

    /**
     * Displays the login page.
     *
     * @return login template
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Handles demo login form submission.
     *
     * @param email email entered by the user
     * @param password password entered by the user
     * @return redirect to home page if successful, otherwise redirect back to login with error
     */
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

    /**
     * Displays the signup page.
     *
     * @return signup template
     */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}