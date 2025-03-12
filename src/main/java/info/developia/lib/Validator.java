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

                if (isUserDefinedClass(value.getClass())) {
                    if (!isValid(value)) return false;
                }
            } catch (Exception e) {
                System.out.println("Error while accessing field: " + field.getName());
            }
        }
        return true;
    }

    private static boolean isUserDefinedClass(Class<?> clazz) {
        return clazz.getClassLoader() != null &&
                !clazz.getName().startsWith("java.") &&
                !clazz.getName().startsWith("javax.");
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

                if (isUserDefinedClass(value.getClass())) {
                    errors.putAll(validate(value));
                }
            } catch (Exception e) {
                System.out.println("Error while accessing field: " + field.getName());
            }
        }
        return errors;
    }
}
