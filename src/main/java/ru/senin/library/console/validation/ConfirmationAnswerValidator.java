package ru.senin.library.console.validation;

import java.util.Locale;
import java.util.Objects;

public class ConfirmationAnswerValidator {

    public ValidationResult validateConfirmationAnswer(String userAnswer) {
        if (userAnswer == null || userAnswer.isBlank()) {
            return ValidationResult.invalid(
                    "Ответ подтверждения не может быть пустым. Введите Да или Нет."
            );
        }

        String normalizedUserAnswer = userAnswer
                .trim()
                .toLowerCase(Locale.ROOT);

        if (!normalizedUserAnswer.equals("да") && !normalizedUserAnswer.equals("нет")) {
            return ValidationResult.invalid(
                    "Введите Да для подтверждения или Нет для отмены."
            );
        }

        return ValidationResult.valid();
    }

    public boolean parseConfirmationAnswer(String userAnswer) {
        Objects.requireNonNull(
                userAnswer,
                "Confirmation answer must not be null."
        );

        return userAnswer
                .trim()
                .equalsIgnoreCase("да");
    }
}
