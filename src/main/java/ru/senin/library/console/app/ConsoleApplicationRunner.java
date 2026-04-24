package ru.senin.library.console.app;

import ru.senin.library.console.command.CommandRoutingResult;
import ru.senin.library.console.command.ConsoleCommandRouter;
import ru.senin.library.console.input.ConsoleInputReader;
import ru.senin.library.console.output.ConsoleApplicationPrinter;
import ru.senin.library.console.validation.MainMenuCommandValidator;

import java.util.Objects;

public class ConsoleApplicationRunner {

    private final ConsoleApplicationPrinter consoleApplicationPrinter;
    private final ConsoleInputReader consoleInputReader;
    private final MainMenuCommandValidator mainMenuCommandValidator;
    private final ConsoleCommandRouter consoleCommandRouter;

    public ConsoleApplicationRunner(
            ConsoleApplicationPrinter consoleApplicationPrinter,
            ConsoleInputReader consoleInputReader,
            MainMenuCommandValidator mainMenuCommandValidator,
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
        this.mainMenuCommandValidator = Objects.requireNonNull(
                mainMenuCommandValidator,
                "Main menu command validator must not be null."
        );
        this.consoleCommandRouter = Objects.requireNonNull(
                consoleCommandRouter,
                "Console command router must not be null."
        );
    }

    public void run() {
        boolean isApplicationRunning = true;

        // TODO [STAGE 14]:
        // Упростить runner:
        // - вынести запуск приложения в отдельный coordinator            (временно не планируется);
        // - отделить жизненный цикл приложения от цикла консольного меню (временно не планируется).

        consoleApplicationPrinter.printApplicationHeader();

        while (isApplicationRunning) {
            consoleApplicationPrinter.printMainMenu();

            String userCommand = consoleInputReader.readValidatedLine(
                    mainMenuCommandValidator::validateCommand,
                    consoleApplicationPrinter::printValidationError
            );

            CommandRoutingResult commandRoutingResult = consoleCommandRouter.routeCommand(userCommand);
            isApplicationRunning = commandRoutingResult == CommandRoutingResult.CONTINUE_APPLICATION;
        }

        // TODO [STAGE 15]:
        // Определить:
        // - где выполняется сохранение данных                    (временно не планируется);
        // - где корректно закрываются ресурсы                    (временно не планируется);
        // - где находится финальная логика завершения приложения (временно не планируется).
    }
}
