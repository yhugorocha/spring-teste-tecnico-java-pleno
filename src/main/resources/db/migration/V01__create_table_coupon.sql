CREATE TABLE coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(6) NOT NULL,
    description VARCHAR(255) NOT NULL,
    discount_value DECIMAL(5,2) NOT NULL,
    expiration_date DATE NOT NULL,
    is_published BOOLEAN NOT NULL DEFAULT FALSE,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX uk_coupon_code ON coupon (code);