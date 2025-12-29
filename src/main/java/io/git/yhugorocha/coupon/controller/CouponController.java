package io.git.yhugorocha.coupon.controller;

import io.git.yhugorocha.coupon.dto.CouponRequestDTO;
import io.git.yhugorocha.coupon.dto.CouponResponseDTO;
import io.git.yhugorocha.coupon.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<CouponResponseDTO> createCoupon(@Valid @RequestBody CouponRequestDTO couponRequestDTO) {
        return ResponseEntity.ok(couponService.createCoupon(couponRequestDTO));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable("code") String code) {
        couponService.deleteCoupon(code);
        return ResponseEntity.noContent().build();
    }
}
