# SaveStack — Design Document

Repository: https://github.com/macydebord/savestack-design  
Team Name: SaveStack Team  
Last Updated: 2026-02-14  

---

## 1. Introduction

SaveStack is a web-based application that allows users to view available coupons, save coupons for later, and redeem saved coupons. The system enforces rules that prevent saving or redeeming coupons that are expired or have reached their usage limit.

SaveStack exposes a REST API endpoint that emits coupon data in JSON format so that another group can consume and use the data.

---

## 2. Storyboard (Mockup Screens)

Storyboard images are located in the `docs/storyboard/` folder.

---

## 3. Functional Requirements

### Requirement 1 — View Available Coupons

As a User  
I want to view available coupons  
So that I can decide which ones to save or redeem  

#### Examples

- Given coupons exist in the system  
  When I open the coupon list  
  Then I see coupons with status ACTIVE  

- Given a coupon is expired  
  When I open the coupon list  
  Then the expired coupon does not appear  

- Given no active coupons exist  
  When I open the coupon list  
  Then the system displays "No coupons available"  

---

### Requirement 2 — Save a Coupon

As a User  
I want to save a coupon  
So that I can use it later  

#### Examples

- Given I am viewing an ACTIVE coupon  
  When I click Save  
  Then the coupon is added to my saved coupons list  

- Given I have already saved the coupon  
  When I click Save again  
  Then the system prevents duplicates  

- Given the coupon is EXPIRED  
  When I attempt to save it  
  Then the system blocks the action  

---

### Requirement 3 — Redeem a Coupon

As a User  
I want to redeem a saved coupon  
So that I can receive the discount  

#### Examples

- Given the coupon is ACTIVE and usageCount is less than usageLimit  
  When I click Redeem  
  Then usageCount increases and the coupon is marked as redeemed for that user  

- Given the coupon is EXPIRED  
  When I click Redeem  
  Then redemption is blocked  

- Given usageCount equals usageLimit  
  When I click Redeem  
  Then redemption is blocked  

---

### Requirement 4 — Automatic Status Updates

As a User  
I want coupon status to update automatically  
So that invalid coupons cannot be saved or redeemed  

#### Examples

- Given today's date is after expiration  
  When the system checks the coupon  
  Then the status becomes EXPIRED  

- Given usageCount equals usageLimit  
  When the system checks the coupon  
  Then the status becomes USED_UP  

---

## 4. Class Diagram (UML)

The UML class diagram is located in the `docs/uml/` folder.

---

## 5. Class Diagram Description

**User**  
Represents a user who can view, save, and redeem coupons.

**Coupon**  
Contains coupon code, discount percentage, expiration date, usage limit, usage count, and status.

**SavedCoupon**  
Represents a saved coupon relationship between a user and a coupon.

**Redemption**  
Represents a record of a coupon being redeemed by a user.

**CouponService**  
Handles business logic and validation rules (save rules, redeem rules, status updates).

**CouponController**  
Provides REST API endpoints.

---

## 6. REST API

### Endpoint

GET `/api/coupons`

Returns coupon data in JSON format.

---

### Example JSON Response

```json
[
  {
    "couponId": 1,
    "code": "SAVE20",
    "discountPercent": 20,
    "expirationDate": "2026-03-01",
    "usageLimit": 50,
    "usageCount": 10,
    "status": "ACTIVE"
  }
]
```

---

### JSON Schema

```json
{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "title": "CouponList",
  "type": "array",
  "items": {
    "type": "object",
    "required": [
      "couponId",
      "code",
      "discountPercent",
      "expirationDate",
      "usageLimit",
      "usageCount",
      "status"
    ],
    "properties": {
      "couponId": { "type": "integer" },
      "code": { "type": "string" },
      "discountPercent": { "type": "integer", "minimum": 1, "maximum": 100 },
      "expirationDate": { "type": "string", "format": "date" },
      "usageLimit": { "type": "integer", "minimum": 1 },
      "usageCount": { "type": "integer", "minimum": 0 },
      "status": {
        "type": "string",
        "enum": ["ACTIVE", "EXPIRED", "USED_UP"]
      }
    }
  }
}
```

---

## 7. Scrum Roles

Product Owner:  
Scrum Master: 
DevOps Lead: 
Developer: 

---

## 8. GitHub Project Board and Milestones

GitHub Project Board: https://github.com/users/macydebord/projects/1

Milestones:

- Milestone #1 — Core features (view, save, redeem)
- Milestone #2 — Search/filter + improvements
- Milestone #3 — Testing and polish

Sprint 1 stories and technical tasks are tracked in GitHub Projects under Milestone #1.

---

## 9. Weekly Standup

Meeting Time: Sunday at 8:00 PM ET  
Platform: Microsoft Teams  
Teams link shared privately.
