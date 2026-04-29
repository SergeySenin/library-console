package ru.senin.library.console.validation;

import java.util.Objects;

/**
 * Результат пользовательской валидации с явным признаком успешности и текстом ошибки.
 *
 * <p>Используется как единый контракт между validator-слоем и
 * {@link ru.senin.library.console.input.ConsoleInputReader}.
 * Объект сообщает, прошло ли значение проверку, и, если
 * проверка не пройдена, содержит человекочитаемое описание причины.
 *
 * <p>Тип намеренно остаётся минимальным: он не хранит коды ошибок, уровни серьёзности и дополнительные данные.
 * Создание экземпляров ограничено фабричными методами, чтобы не допускать противоречивых состояний.
 */
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
