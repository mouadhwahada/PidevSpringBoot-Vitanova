package tn.spring.pispring.ServiceIMP.admin.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Coupon;
import tn.spring.pispring.exceptions.ValidationException;
import tn.spring.pispring.repo.CouponRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {


    private final CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())){
            throw  new ValidationException("Coupon code already exists");
        }
        return couponRepository.save(coupon);

    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }

}
