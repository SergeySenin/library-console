package ru.senin.library.console.input;

import ru.senin.library.console.validation.ValidationResult;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Компонент чтения пользовательского ввода из консоли с циклом повторного запроса.
 *
 * <p>Читает строку, выполняет минимальную нормализацию через {@code trim()},
 * передаёт значение validator-у и, при ошибке, делегирует вывод сообщения через callback printer-а.
 * Поддерживает обязательный и опциональный режимы валидируемого ввода.
 *
 * <p>Класс не содержит предметных правил для книг, меню или подтверждений удаления.
 * Он отвечает только за механику чтения и повторения, оставляя смысловую проверку validator-слою.
 */
public class ConsoleInputReader {

    private final Scanner consoleScanner;

    public ConsoleInputReader() {
        this.consoleScanner = new Scanner(System.in);
    }

    public String readValidatedLine(
            Function<String, ValidationResult> validator,
            Consumer<String> validationErrorPrinter
    ) {
        Objects.requireNonNull(
                validator,
                "Validator must not be null."
        );
        Objects.requireNonNull(
                validationErrorPrinter,
                "Validation error printer must not be null."
        );

        while (true) {
            String enteredValue = consoleScanner
                    .nextLine()
                    .trim();

            ValidationResult validationResult = validator.apply(enteredValue);

            if (validationResult.isValid()) {
                return enteredValue;
            }

            validationErrorPrinter.accept(validationResult.getErrorMessage());
        }
    }

    public String readOptionalValidatedLine(
            Function<String, ValidationResult> validator,
            Consumer<String> validationErrorPrinter
    ) {
        Objects.requireNonNull(
                validator,
                "Validator must not be null."
        );
        Objects.requireNonNull(
                validationErrorPrinter,
                "Validation error printer must not be null."
        );

        while (true) {
            String enteredValue = consoleScanner
                    .nextLine()
                    .trim();

            if (enteredValue.isEmpty()) {
                return "";
            }

            ValidationResult validationResult = validator.apply(enteredValue);

            if (validationResult.isValid()) {
                return enteredValue;
            }

            validationErrorPrinter.accept(validationResult.getErrorMessage());
        }
    }
}
