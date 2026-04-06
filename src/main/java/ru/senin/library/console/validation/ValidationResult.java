package ru.senin.library.console.validation;

import java.util.Objects;

public final class ValidationResult {

    private final boolean valid;
    private final String errorMessage;

    private ValidationResult(
            boolean valid,
            String errorMessage
    ) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public static ValidationResult valid() {
        return new ValidationResult(
                true,
                ""
        );
    }

    public static ValidationResult invalid(String errorMessage) {
        Objects.requireNonNull(
                errorMessage,
                "Validation error message must not be null."
        );

        if (errorMessage.isBlank()) {
            throw new IllegalArgumentException("Validation error message must not be blank.");
        }

        return new ValidationResult(
                false,
                errorMessage
        );
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
