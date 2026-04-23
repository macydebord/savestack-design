package com.EnterpriseApp.savestack.entities;

import java.time.LocalDate;

public class Coupon {

    private Long id;
    private String title;
    private String code;
    private String description;
    private int discountPercent;
    private LocalDate expirationDate;
    private int usageLimit;
    private int usageCount;
    private CouponStatus status;

    public Coupon(Long id, String title, String code, String description,
                  int discountPercent, LocalDate expirationDate,
                  int usageLimit) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.description = description;
        this.discountPercent = discountPercent;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
        this.usageCount = 0;
        this.status = CouponStatus.ACTIVE;
    }

    // Getters & Setters

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public int getDiscountPercent() { return discountPercent; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public int getUsageLimit() { return usageLimit; }
    public int getUsageCount() { return usageCount; }
    public CouponStatus getStatus() { return status; }

    public void setUsageCount(int usageCount) { this.usageCount = usageCount; }
    public void setStatus(CouponStatus status) { this.status = status; }
}