package com.EnterpriseApp.savestack.entities;

import java.time.LocalDate;

/**
 * Represents a coupon that users can view, save, and redeem in SaveStack.
 */
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

    /**
     * Creates an empty coupon.
     * This constructor is needed for Thymeleaf form binding.
     */
    public Coupon() {
        this.usageCount = 0;
        this.status = CouponStatus.ACTIVE;
    }

    /**
     * Creates a new coupon.
     *
     * @param id unique coupon ID
     * @param title coupon title
     * @param code coupon redemption code
     * @param description description of the coupon offer
     * @param discountPercent discount percentage or dollar-style display value
     * @param expirationDate date when the coupon expires
     * @param usageLimit maximum number of times the coupon can be redeemed
     */
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

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getDiscountPercent() { return discountPercent; }

    public void setDiscountPercent(int discountPercent) { this.discountPercent = discountPercent; }

    public LocalDate getExpirationDate() { return expirationDate; }

    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public int getUsageLimit() { return usageLimit; }

    public void setUsageLimit(int usageLimit) { this.usageLimit = usageLimit; }

    public int getUsageCount() { return usageCount; }

    /**
     * Updates the number of times this coupon has been redeemed.
     *
     * @param usageCount new usage count
     */
    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }

    public CouponStatus getStatus() { return status; }

    /**
     * Updates the coupon status.
     *
     * @param status new coupon status
     */
    public void setStatus(CouponStatus status) {
        this.status = status;
    }
}