package io.git.yhugorocha.coupon.mock;

import io.git.yhugorocha.coupon.dto.CouponRequestDTO;
import io.git.yhugorocha.coupon.dto.CouponResponseDTO;
import io.git.yhugorocha.coupon.entity.CouponEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CouponMock {

    public static CouponRequestDTO createCouponRequestDTO() {
        return CouponRequestDTO.builder()
                .code("ABC123")
                .description("10% off on all items")
                .discountValue(new java.math.BigDecimal("10.0"))
                .expirationDate(LocalDateTime.now().plusDays(30))
                .isPublished(true)
                .build();
    }

    public static CouponResponseDTO createCouponResponseDTO() {
        return CouponResponseDTO.builder()
                .id(1L)
                .code("ABC123")
                .description("10% off on all items")
                .discountValue(new java.math.BigDecimal("10.0"))
                .expirationDate(LocalDateTime.now().plusDays(30))
                .isPublished(true)
                .createdAt(LocalDateTime.now())
                .build(
        );
    }

    public static CouponEntity createCouponEntity() {
        return CouponEntity.builder()
                .id(1L)
                .code("ABC123")
                .description("10% off on all items")
                .discountValue(new BigDecimal("10.0"))
                .expirationDate(LocalDateTime.now().plusDays(30))
                .isPublished(true)
                .isDeleted(false)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
