package info.developia.lib.validator;

import info.developia.lib.annotation.Length;

public class Validators {

    public static boolean isNotNull(Object value) {
        return value != null;
    }

    public static boolean isLength(Object value, Length annotation) {
        if (!(value instanceof String)) return false;
        int length = ((String) value).length();
        return length >= annotation.min() && length <= annotation.max();
    }
}
