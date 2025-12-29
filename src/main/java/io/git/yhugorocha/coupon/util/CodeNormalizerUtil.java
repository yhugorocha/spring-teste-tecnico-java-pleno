package io.git.yhugorocha.coupon.util;

import java.text.Normalizer;

public class CodeNormalizerUtil {

    private CodeNormalizerUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String normalize(String code) {
        if (code == null) return null;

        return Normalizer.normalize(code, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^A-Za-z0-9]", "")
                .toUpperCase();
    }
}
