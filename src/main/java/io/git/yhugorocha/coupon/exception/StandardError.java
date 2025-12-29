package io.git.yhugorocha.coupon.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer statusCode;
    private String errorCode;
    private String message;
    private Long timestamp;
}
