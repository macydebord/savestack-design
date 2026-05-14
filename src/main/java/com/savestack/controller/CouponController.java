package com.savestack.controller;

import com.savestack.model.CouponStatus;

public class CouponController {

    import com.savestack.model.Coupon;
    import com.savestack.model.CouponStatus;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;

    @RestController
    public class CouponController {

        @GetMapping("/api/coupons")
        public List<Coupon> getCoupons(
                @RequestParam(required = false)CouponStatus status,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "20") int size
                ){
            return List.of();
        }
    }
}
