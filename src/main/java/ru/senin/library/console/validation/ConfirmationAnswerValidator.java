package ru.senin.library.console.validation;

import java.util.Locale;
import java.util.Objects;

/**
 * Валидатор подтверждающего ответа пользователя для сценария удаления книги.
 *
 * <p>Проверяет, что введённое значение соответствует допустимому ответу подтверждения, и
 * предоставляет отдельный шаг преобразования такого ответа в логический результат пользовательского выбора.
 *
 * <p>Класс намеренно изолирован от {@link BookInputValidator}, потому что
 * подтверждение удаления является самостоятельным UI-сценарием с отдельной семантикой.
 * Он не знает о каталоге, printer-ах и предметной модели книги.
 */
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
