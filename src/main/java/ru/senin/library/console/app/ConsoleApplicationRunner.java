package ru.senin.library.console.app;

import ru.senin.library.console.command.CommandRoutingResult;
import ru.senin.library.console.command.ConsoleCommandRouter;
import ru.senin.library.console.input.ConsoleInputReader;
import ru.senin.library.console.output.ConsoleApplicationPrinter;

import java.util.Objects;

public class ConsoleApplicationRunner {

    private final ConsoleApplicationPrinter consoleApplicationPrinter;
    private final ConsoleInputReader consoleInputReader;
    private final ConsoleCommandRouter consoleCommandRouter;

    public ConsoleApplicationRunner(
            ConsoleApplicationPrinter consoleApplicationPrinter,
            ConsoleInputReader consoleInputReader,
            ConsoleCommandRouter consoleCommandRouter
    ) {
        this.consoleApplicationPrinter = Objects.requireNonNull(
                consoleApplicationPrinter,
                "Console application printer must not be null."
        );
        this.consoleInputReader = Objects.requireNonNull(
                consoleInputReader,
                "Console input reader must not be null."
        );
        this.consoleCommandRouter = Objects.requireNonNull(
                consoleCommandRouter,
                "Console command router must not be null."
        );
    }

    public void run() {
        boolean isApplicationRunning = true;

        // TODO [STAGE 1]:
        // Это стартовый runner консольного приложения.
        // (возможно) Позже отсюда нужно будет вынести:
        // 1. запуск приложения в отдельный coordinator;
        // 2. общий жизненный цикл приложения;
        // 3. обработку завершения и сохранения данных.

        consoleApplicationPrinter.printApplicationHeader();

        while (isApplicationRunning) {
            consoleApplicationPrinter.printMainMenu();

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
