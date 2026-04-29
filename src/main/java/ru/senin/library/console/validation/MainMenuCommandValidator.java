package ru.senin.library.console.validation;

import java.util.Set;

/**
 * Валидатор команд главного меню консольного приложения.
 *
 * <p>Проверяет, что пользователь ввёл одну из допустимых строковых команд верхнего уровня, и
 * возвращает {@link ValidationResult} вместо использования исключений как штатного механизма ветвления.
 *
 * <p>Класс не маршрутизирует команды и не знает, какие действия за ними стоят.
 * Его задача ограничена UX-проверкой ввода перед передачей команды в
 * {@link ru.senin.library.console.command.ConsoleCommandRouter}.
 */
public class MainMenuCommandValidator {

    private static final Set<String> ALLOWED_COMMANDS = Set.of(
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8"
    );

    public ValidationResult validateCommand(String userCommand) {
        if (userCommand == null || userCommand.isBlank()) {
            return ValidationResult.invalid(
                    "Команда не может быть пустой. Введите одну из команд: 0, 1, 2, 3, 4, 5, 6, 7, 8."
            );
        }

        if (!ALLOWED_COMMANDS.contains(userCommand)) {
            return ValidationResult.invalid(
                    "Неизвестная команда. Доступные команды: 0, 1, 2, 3, 4, 5, 6, 7, 8."
            );
        }

        return ValidationResult.valid();
    }
}
