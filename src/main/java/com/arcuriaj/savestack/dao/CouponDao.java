package com.arcuriaj.savestack.dao;

import com.arcuriaj.savestack.entities.Coupon;

import java.util.List;
import java.util.Optional;


public interface CouponDao {
    Optional<Coupon> findById(Long id);
    List<Coupon> findByName(String name);
    Coupon save(Coupon coupon);
}
