package io.git.yhugorocha.coupon.service.impl;

import io.git.yhugorocha.coupon.dto.CouponRequestDTO;
import io.git.yhugorocha.coupon.entity.CouponEntity;
import io.git.yhugorocha.coupon.exception.BusinessException;
import io.git.yhugorocha.coupon.mock.CouponMock;
import io.git.yhugorocha.coupon.repository.CouponRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceImpTest {

    @Mock
    private CouponRepository couponRepository;
    private CouponServiceImpl couponService;
    private static Validator validator;

    @BeforeEach
    void setUp() {
        couponService = spy(new CouponServiceImpl(couponRepository));
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Order(1)
    @Test
    @DisplayName("Should create coupon successfully when code does not exist")
    void shouldCreateCouponSuccessfully_whenCodeDoesNotExist() {
        //GIVEN
        var couponRequestDTO = CouponMock.createCouponRequestDTO();

        //WHEN
        Set<ConstraintViolation<CouponRequestDTO>> violations = validator.validate(couponRequestDTO);
        when(couponRepository.findByCode(anyString())).thenReturn(Optional.empty());
        doNothing().when(couponService).normalizeCode(any());
        when(couponRepository.save(any())).thenReturn(CouponMock.createCouponEntity());

        //THEN
        var response = couponService.createCoupon(couponRequestDTO);

        assertTrue(violations.isEmpty());
        assertNotNull(response);
        verify(couponRepository, times(1)).findByCode(anyString());
        verify(couponService, times(1)).normalizeCode(any(CouponEntity.class));
        verify(couponRepository, times(1)).save(any());
    }

    @Order(2)
    @Test
    @DisplayName("Should catch Business Exception when code does exist")
    void shouldCatchBusinessException_whenCodeDoesExist() {
        //GIVEN
        var couponRequestDTO = CouponMock.createCouponRequestDTO();

        //WHEN
        Set<ConstraintViolation<CouponRequestDTO>> violations = validator.validate(couponRequestDTO);
        when(couponRepository.findByCode(anyString())).thenReturn(Optional.of(CouponMock.createCouponEntity()));

        //THEN
        assertTrue(violations.isEmpty());
        assertThrows(BusinessException.class, () -> {
            couponService.createCoupon(couponRequestDTO);
        });
    }

    @Order(3)
    @Test
    @DisplayName("Should throw BusinessException when trying to delete a non-existent coupon")
    void shouldThrowBusinessException_whenDeletingNonExistentCoupon() {
        //GIVEN
        var code = "ABC123";

        //WHEN
        when(couponRepository.findByCode(anyString())).thenReturn(Optional.empty());

        //THEN
        assertThrows(BusinessException.class, () -> {
            couponService.deleteCoupon(code);
        });
    }

    @Order(4)
    @Test
    @DisplayName("Should throw BusinessException when trying to delete an already deleted coupon")
    void shouldThrowBusinessException_whenDeletingAlreadyDeletedCoupon() {
        //GIVEN
        var code = "ABC123";
        var couponEntity = CouponMock.createCouponEntity();
        couponEntity.setIsDeleted(true);

        //WHEN
        when(couponRepository.findByCode(anyString())).thenReturn(Optional.of(couponEntity));

        //THEN
        assertThrows(BusinessException.class, () -> {
            couponService.deleteCoupon(code);
        });
    }

    @Order(5)
    @Test
    @DisplayName("Should mark coupon as deleted when code exists")
    void shouldMarkCouponAsDeleted_whenCodeExists() {
        //GIVEN
        var code = "ABC123";
        var couponEntity = CouponMock.createCouponEntity();

        //WHEN
        when(couponRepository.findByCode(anyString())).thenReturn(Optional.of(couponEntity));

        //THEN
        couponService.deleteCoupon(code);

        assertTrue(couponEntity.getIsDeleted());
        verify(couponRepository, times(1)).findByCode(anyString());
        verify(couponRepository, times(1)).save(any());
    }

}
