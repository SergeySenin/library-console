package ru.senin.library;

import ru.senin.library.console.ConsoleApplicationRunner;
import ru.senin.library.console.ConsoleInputReader;
import ru.senin.library.console.ConsolePrinter;

public class LibraryApplication {

    public static void main(String[] args) {
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();
        ConsolePrinter consolePrinter = new ConsolePrinter();

        ConsoleApplicationRunner runner = new ConsoleApplicationRunner(consoleInputReader, consolePrinter);

        runner.run();

        // TODO [STAGE 3]:
        // Позже создание объектов приложения нужно будет перенести в более явную точку конфигурации.
    }
}
