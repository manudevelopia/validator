package info.developia.lib.validator;

import info.developia.lib.annotation.Length;

public class Validators {

    public static boolean isNotNull(Object value) {
        return value != null;
    }

    public static boolean isLength(Object value, Length annotation) {
        if (!(value instanceof String valueString)) return false;
        int length = valueString.length();
        return length >= annotation.min() && length <= annotation.max();
    }

    public static boolean isNumber(Object value, info.developia.lib.annotation.Number annotation) {
        if (!(value instanceof Number number)) return false;
        long longValue = number.longValue();
        return longValue >= annotation.min() && longValue <= annotation.max() && (!annotation.positive() || longValue > 0);
    }
}
