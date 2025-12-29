package io.git.yhugorocha.coupon.exception;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardFieldError {

    private Integer statusCode;
    private String message;
    private Long timestamp;
    private List<FieldError> errors;
}
