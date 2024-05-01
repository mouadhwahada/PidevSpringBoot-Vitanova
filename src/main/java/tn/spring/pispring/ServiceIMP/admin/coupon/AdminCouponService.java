package tn.spring.pispring.ServiceIMP.admin.coupon;

import tn.spring.pispring.Entities.Coupon;

import java.util.List;

public interface AdminCouponService {

    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
}
