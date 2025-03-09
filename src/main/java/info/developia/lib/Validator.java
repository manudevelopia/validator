package info.developia.lib;

import info.developia.lib.annotation.Length;
import info.developia.lib.annotation.NotNull;
import info.developia.lib.validator.Validators;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class Validator {

    public static boolean isValid(Object object) {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                VarHandle varHandle = MethodHandles.privateLookupIn(clazz, MethodHandles.lookup()).unreflectVarHandle(field);
                Object value = varHandle.get(object);

                if (field.isAnnotationPresent(NotNull.class)) {
                    if (!Validators.isNotNull(value)) return false;
                }

                if (field.isAnnotationPresent(Length.class)) {
                    if (!Validators.isLength(value, field.getAnnotation(Length.class))) return false;
                }
            } catch (IllegalAccessException e) {
                System.out.println("Cannot access field: " + field.getName());
            } catch (Exception e) {
                System.out.println("Other error while accessing field: " + field.getName());
            }
        }
        return true;
    }

    public static ValidationResult is(Object obj) {
        var errors = validate(obj);
        return new ValidationResult(errors.isEmpty(), errors);
    }

    public static Map<String, String> validate(Object obj) {
        Map<String, String> errors = new LinkedHashMap<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            try {
                VarHandle varHandle = MethodHandles.privateLookupIn(clazz, MethodHandles.lookup()).unreflectVarHandle(field);
                Object value = varHandle.get(obj);

                if (field.isAnnotationPresent(NotNull.class)) {
                    if (!Validators.isNotNull(value)) {
                        NotNull annotation = field.getAnnotation(NotNull.class);
                        errors.put(field.getName(), annotation.message());
                    }
                }

                if (field.isAnnotationPresent(Length.class)) {
                    Length annotationLength = field.getAnnotation(Length.class);
                    if (!Validators.isLength(value, annotationLength)) {
                        errors.put(field.getName(), annotationLength.message() + " (Expected: " + annotationLength.min() + "-" + annotationLength.max() + ")");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error while accessing field: " + field.getName());
            }
        }
        return errors;
    }
}


//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Validator {
//    public static List<String> validate(Object obj) {
//        List<String> errors = new ArrayList<>();
//        Class<?> clazz = obj.getClass();
//
//        for (Field field : clazz.getDeclaredFields()) {
//            try {
//                // Use MethodHandles instead of setAccessible(true)
//                VarHandle varHandle = MethodHandles.privateLookupIn(clazz, MethodHandles.lookup())
//                        .unreflectVarHandle(field);
//                Object value = varHandle.get(obj);
//
//                // Validate @NotNull
//                if (field.isAnnotationPresent(NotNull.class) && value == null) {
//                    NotNull annotation = field.getAnnotation(NotNull.class);
//                    errors.add(annotation.message());
//                }
//
//                // Validate @Min (for numeric fields)
//                if (field.isAnnotationPresent(Min.class) && value instanceof Integer) {
//                    Min annotation = field.getAnnotation(Min.class);
//                    if ((Integer) value < annotation.value()) {
//                        errors.add(annotation.message());
//                    }
//                }
//
//                // ✅ Validate @Length for String fields
//                if (field.isAnnotationPresent(Length.class) && value instanceof String) {
//                    Length annotation = field.getAnnotation(Length.class);
//                    int length = ((String) value).length();
//                    if (length < annotation.min() || length > annotation.max()) {
//                        errors.add(annotation.message() + " (Expected: " + annotation.min() + "-" + annotation.max() + ")");
//                    }
//                }
//
//            } catch (IllegalAccessException e) {
//                errors.add("Cannot access field: " + field.getName());
//            }
//        }
//        return errors;
//    }
//}
