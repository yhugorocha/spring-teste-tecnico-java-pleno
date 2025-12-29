package io.git.yhugorocha.coupon.dto;

import io.git.yhugorocha.coupon.entity.CouponEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponResponseDTO {

    private Long id;
    private String code;
    private String description;
    private BigDecimal discountValue;
    private LocalDateTime expirationDate;
    private Boolean isPublished;
    private LocalDateTime createdAt;

    public static CouponResponseDTO fromEntity(CouponEntity entity) {
        return CouponResponseDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .description(entity.getDescription())
                .discountValue(entity.getDiscountValue())
                .expirationDate(entity.getExpirationDate())
                .isPublished(entity.getIsPublished())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
