package ru.senin.library.console;

public class ConsoleApplicationRunner {

    private final ConsolePrinter consolePrinter;
    private final ConsoleInputReader consoleInputReader;
    private final ConsoleCommandRouter consoleCommandRouter;

    public ConsoleApplicationRunner(
            ConsolePrinter consolePrinter,
            ConsoleInputReader consoleInputReader,
            ConsoleCommandRouter consoleCommandRouter
    ) {
        this.consolePrinter = consolePrinter;
        this.consoleInputReader = consoleInputReader;
        this.consoleCommandRouter = consoleCommandRouter;
    }

    public void run() {
        boolean isApplicationRunning = true;

        // TODO [STAGE 1]:
        // Это стартовый runner консольного приложения.
        // (возможно) Позже отсюда нужно будет вынести:
        // 1. запуск приложения в отдельный coordinator;
        // 2. общий жизненный цикл приложения;
        // 3. обработку завершения и сохранения данных.

        consolePrinter.printApplicationHeader();

        while (isApplicationRunning) {
            consolePrinter.printMainMenu();

            String userCommand = consoleInputReader.readCommand();
            CommandRoutingResult commandRoutingResult = consoleCommandRouter.routeCommand(userCommand);
            isApplicationRunning = commandRoutingResult == CommandRoutingResult.CONTINUE_APPLICATION;
        }

        // TODO [STAGE 2]:
        // Позже нужно определить:
        // - где выполняется сохранение данных;
        // - где корректно закрываются ресурсы;
        // - где находится финальная логика завершения приложения.
    }
}
