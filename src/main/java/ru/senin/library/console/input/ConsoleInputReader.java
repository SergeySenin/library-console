package ru.senin.library.console.input;

import ru.senin.library.console.validation.ValidationResult;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

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
