package ru.senin.library.console.input;

import java.time.DateTimeException;
import java.time.Year;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleInputReader {

    private final Scanner consoleScanner;

    public ConsoleInputReader() {
        this.consoleScanner = new Scanner(System.in);
    }

    public String readCommand() {
        return consoleScanner
                .nextLine()
                .trim();
    }

    public String readRequiredText(String fieldDisplayName) {
        Objects.requireNonNull(
                fieldDisplayName,
                "Field display name must not be null."
        );

        if (fieldDisplayName.isBlank()) {
            throw new IllegalArgumentException("Field display name must not be blank.");
        }

        while (true) {
            String enteredValue = consoleScanner
                    .nextLine()
                    .trim();

            if (!enteredValue.isEmpty()) {
                return enteredValue;
            }

            System.out.print(
                    "Поле \""
                            + fieldDisplayName
                            + "\" не может быть пустым. Повторите ввод: "
            );
        }
    }

    public Year readPublicationYear() {
        while (true) {
            String enteredValue = consoleScanner
                    .nextLine()
                    .trim();

            try {
                int yearValue = Integer.parseInt(enteredValue);
                return Year.of(yearValue);
            } catch (NumberFormatException | DateTimeException exception) {
                System.out.print("Некорректный год издания. Введите год в формате YYYY, например 2020: ");
            }
        }
    }

    // TODO [STAGE 4]:
    // Позже этот класс будет расширен методами для чтения:
    // - yes/no ответов;
    // - числовых диапазонов;
    // - дат;
    // - подтверждений действий.
}
