package ru.senin.library.console.command;

/**
 * Минимальный контракт выполняемой консольной команды.
 *
 * <p>Инкапсулирует действие, которое может быть зарегистрировано в {@link ConsoleCommandRouter}
 * по строковому ключу и выполнено без знания конкретного пользовательского сценария.
 *
 * <p>Результатом выполнения команды является {@link CommandRoutingResult},
 * определяющий, должен ли жизненный цикл приложения продолжаться или завершиться.
 */
@FunctionalInterface
public interface ConsoleCommand {

    CommandRoutingResult execute();
}
