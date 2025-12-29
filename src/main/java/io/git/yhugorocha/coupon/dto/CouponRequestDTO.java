package io.git.yhugorocha.coupon.dto;

import io.git.yhugorocha.coupon.entity.CouponEntity;
import io.git.yhugorocha.coupon.util.CodeNormalizerUtil;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponRequestDTO {

    @Size(min = 6, max = 6, message = "Code must be exactly 6 characters long")
    @NotEmpty
    private String code;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Discount value cannot be null")
    @DecimalMin(value = "0.5", inclusive = true, message = "Discount value must be at least 0.5")
    @DecimalMax(value = "100.0", message = "Discount value cannot exceed 100")
    private BigDecimal discountValue;
    @NotNull(message = "Expiration date cannot be null")
    @Future(message = "Expiration date must be in the future")
    private LocalDateTime expirationDate;
    private Boolean isPublished;

    public CouponEntity toEntity() {
        return CouponEntity.builder()
                .code(CodeNormalizerUtil.normalize(this.code))
                .description(this.description)
                .discountValue(this.discountValue)
                .expirationDate(this.expirationDate)
                .isPublished(this.isPublished != null && this.isPublished)
                .isDeleted(false)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
