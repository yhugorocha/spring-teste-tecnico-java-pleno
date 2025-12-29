package io.git.yhugorocha.coupon.service.impl;

import io.git.yhugorocha.coupon.dto.CouponRequestDTO;
import io.git.yhugorocha.coupon.dto.CouponResponseDTO;
import io.git.yhugorocha.coupon.exception.BusinessException;
import io.git.yhugorocha.coupon.repository.CouponRepository;
import io.git.yhugorocha.coupon.service.CouponService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    @Override
    public CouponResponseDTO createCoupon(CouponRequestDTO couponRequestDTO) {
        var savedCoupon = couponRequestDTO.toEntity();
        var existingCoupon = couponRepository.findByCode(savedCoupon.getCode());
        if (existingCoupon.isPresent()) {
            throw new BusinessException("Coupon code already exists");
        }
        couponRepository.save(savedCoupon);
        return CouponResponseDTO.fromEntity(savedCoupon);
    }

    @Transactional
    @Override
    public void deleteCoupon(String code) {
        var coupon = couponRepository.findByCode(code).orElseThrow(() -> new BusinessException("Coupon not found"));

        if (coupon.getIsDeleted()) {
            throw new BusinessException("Coupon is already deleted");
        }

        coupon.setIsDeleted(true);
        couponRepository.save(coupon);
    }
}
