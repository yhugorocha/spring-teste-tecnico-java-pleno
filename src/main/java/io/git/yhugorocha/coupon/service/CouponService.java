package io.git.yhugorocha.coupon.service;

import io.git.yhugorocha.coupon.dto.CouponRequestDTO;
import io.git.yhugorocha.coupon.dto.CouponResponseDTO;

public interface CouponService {

    CouponResponseDTO createCoupon(CouponRequestDTO couponRequestDTO);
    void deleteCoupon(String code);
}
