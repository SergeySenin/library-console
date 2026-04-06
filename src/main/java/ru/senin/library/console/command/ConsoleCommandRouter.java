package ru.senin.library.console.command;

import ru.senin.library.console.handler.ConsoleBookHandler;
import ru.senin.library.console.output.ConsoleApplicationPrinter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ConsoleCommandRouter {

    private final Map<String, ConsoleCommand> commandRegistry;
    private final ConsoleApplicationPrinter consoleApplicationPrinter;

    public ConsoleCommandRouter(
            ConsoleApplicationPrinter consoleApplicationPrinter,
            ConsoleBookHandler consoleBookHandler
    ) {
        this.consoleApplicationPrinter = Objects.requireNonNull(
                consoleApplicationPrinter,
                "Console application printer must not be null."
        );
        this.commandRegistry = createCommandRegistry(consoleBookHandler);
    }

    public CommandRoutingResult routeCommand(String userCommand) {
        Objects.requireNonNull(
                userCommand,
                "User command must not be null."
        );

        ConsoleCommand consoleCommand = commandRegistry.get(userCommand);

        if (consoleCommand == null) {
            consoleApplicationPrinter.printUnknownCommandMessage(userCommand);
            return CommandRoutingResult.CONTINUE_APPLICATION;
        }

        return consoleCommand.execute();
    }

    private Map<String, ConsoleCommand> createCommandRegistry(ConsoleBookHandler consoleBookHandler) {
        Objects.requireNonNull(
                consoleBookHandler,
                "Console book handler must not be null."
        );

        LinkedHashMap<String, ConsoleCommand> mutableCommandRegistry = new LinkedHashMap<>();

        registerCommand(
                mutableCommandRegistry,
                "0",
                this::finishApplication
        );

        registerCommand(
                mutableCommandRegistry,
                "1",
                () -> executeAndContinueApplication(consoleBookHandler::showAllBooks)
        );

        registerCommand(
                mutableCommandRegistry,
                "2",
                () -> executeAndContinueApplication(consoleBookHandler::registerNewBook)
        );

        registerCommand(
                mutableCommandRegistry,
                "3",
                () -> executeAndContinueApplication(consoleBookHandler::searchBooksByTitle)
        );

        return Collections.unmodifiableMap(mutableCommandRegistry);
    }

    private void registerCommand(
            Map<String, ConsoleCommand> mutableCommandRegistry,
            String commandKey,
            ConsoleCommand consoleCommand
    ) {
        Objects.requireNonNull(
                mutableCommandRegistry,
                "Command registry must not be null."
        );
        Objects.requireNonNull(
                consoleCommand,
                "Console command must not be null."
        );

        if (commandKey == null || commandKey.isBlank()) {
            throw new IllegalArgumentException("Command key must not be null or blank.");
        }

        if (mutableCommandRegistry.containsKey(commandKey)) {
            throw new IllegalStateException(
                    "Command with key \""
                            + commandKey
                            + "\" is already registered."
            );
        }

        mutableCommandRegistry.put(
                commandKey,
                consoleCommand
        );
    }

    private CommandRoutingResult finishApplication() {
        consoleApplicationPrinter.printApplicationFinishedMessage();

        return CommandRoutingResult.STOP_APPLICATION;
    }

    private CommandRoutingResult executeAndContinueApplication(Runnable action) {
        Objects.requireNonNull(
                action,
                "Action must not be null."
        );

        action.run();

        return CommandRoutingResult.CONTINUE_APPLICATION;
    }

    // TODO [STAGE 14]:
    // Позже маршрутизацию команд можно расширить:
    // - вынести описание команд в отдельную конфигурацию;
    // - (возможно) добавить поддержку вложенных меню;
    // - (возможно) ввести отдельный контекст текущего меню;
    // - (возможно) подключить разные наборы команд для разных разделов приложения.
}
