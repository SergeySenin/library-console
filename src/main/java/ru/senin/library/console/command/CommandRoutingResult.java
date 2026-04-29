package ru.senin.library.console.command;

/**
 * Явный результат выполнения верхнеуровневой консольной команды.
 *
 * <p>Используется как устойчивый контракт между
 * {@link ConsoleCommandRouter} и {@link ru.senin.library.console.app.ConsoleApplicationRunner}
 * вместо слабосемантического {@code boolean}.
 *
 * <p>Перечисление намеренно остаётся маленьким и отражает только текущее состояние жизненного цикла приложения:
 * продолжение работы или завершение.
 */
public enum CommandRoutingResult {

    CONTINUE_APPLICATION,
    STOP_APPLICATION
}
