package ru.senin.library.console;

public class ConsoleApplicationRunner {

    private final ConsoleInputReader consoleInputReader;
    private final ConsolePrinter consolePrinter;
    private final ConsoleBookHandler consoleBookHandler;

    public ConsoleApplicationRunner(
            ConsoleInputReader consoleInputReader,
            ConsolePrinter consolePrinter,
            ConsoleBookHandler consoleBookHandler
    ) {
        this.consoleInputReader = consoleInputReader;
        this.consolePrinter = consolePrinter;
        this.consoleBookHandler = consoleBookHandler;
    }

    public void run() {
        boolean isApplicationRunning = true;

        // TODO [STAGE 1]:
        // Это стартовый runner консольного приложения.
        // Позже отсюда нужно будет вынести:
        // 1. маршрутизацию команд в отдельный handler registry;
        // 2. регистрацию команд в отдельную структуру;
        // 3. переходы между вложенными меню.

        consolePrinter.printApplicationHeader();

        while (isApplicationRunning) {
            consolePrinter.printMainMenu();
            String userCommand = consoleInputReader.readCommand();

            switch (userCommand) {
                case "0":
                    consolePrinter.printApplicationFinishedMessage();
                    isApplicationRunning = false;
                    break;
                case "1":
                    consoleBookHandler.showAllBooks();
                    break;
                case "2":
                    consoleBookHandler.registerNewBook();
                    break;
                case "3":
                    consoleBookHandler.searchBooksByTitle();
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
