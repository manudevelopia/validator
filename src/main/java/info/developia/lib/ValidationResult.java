package info.developia.lib;

import java.util.Map;

public class ValidationResult {
    private final boolean valid;
    private final Map<String, String> errors;

    public ValidationResult(boolean valid, Map<String, String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    public boolean valid() {
        return valid;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
