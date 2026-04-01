package ru.senin.library;

import ru.senin.library.console.ConsoleInputReader;
import ru.senin.library.console.ConsolePrinter;

public class LibraryApplication {

    public static void main(String[] args) {
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        boolean isApplicationRunning = true;

        // TODO [STAGE 1]:
        // Это стартовый координирующий класс приложения.
        // Позже отсюда нужно будет вынести:
        // 1. цикл обработки команд в отдельный runner;
        // 2. маршрутизацию команд в отдельный handler;
        // 3. создание объектов в более явную точку конфигурации.

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
