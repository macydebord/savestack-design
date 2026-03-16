package com.arcuriaj.savestack.repository;

import java.util.List;
import com.arcuriaj.savestack.model.SavedCoupon;

public interface CouponRepository {

    List<SavedCoupon> findByUserId(Long userId);

}
