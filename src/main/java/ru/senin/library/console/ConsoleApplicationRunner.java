package ru.senin.library.console;

public class ConsoleApplicationRunner {

    private final ConsoleInputReader consoleInputReader;
    private final ConsolePrinter consolePrinter;

    public ConsoleApplicationRunner(
            ConsoleInputReader consoleInputReader,
            ConsolePrinter consolePrinter
    ) {
        this.consoleInputReader = consoleInputReader;
        this.consolePrinter = consolePrinter;
    }

    public void run() {
        boolean isApplicationRunning = true;

        // TODO [STAGE 1]:
        // Это стартовый runner консольного приложения.
        // Позже отсюда нужно будет вынести:
        // 1. маршрутизацию команд в отдельный handler;
        // 2. регистрацию команд в отдельную структуру;
        // 3. переходы между вложенными меню.

        consolePrinter.printApplicationHeader();

        while (isApplicationRunning) {
            consolePrinter.printMainMenu();
            String userCommand = consoleInputReader.readCommand();

            switch (userCommand) {
                case "1":
                    consolePrinter.printTestActionResult();
                    break;
                case "0":
                    consolePrinter.printApplicationFinishedMessage();
                    isApplicationRunning = false;
                    break;
                default:
                    consolePrinter.printUnknownCommandMessage(userCommand);
                    break;
            }
        }

        // TODO [STAGE 2]:
        // Позже нужно определить:
        // - где выполняется сохранение данных;
        // - где корректно закрываются ресурсы;
        // - где находится финальная логика завершения приложения.
    }
}
