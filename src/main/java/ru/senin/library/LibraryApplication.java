package ru.senin.library;

import ru.senin.library.book.BookCatalog;
import ru.senin.library.console.ConsoleBookHandler;
import ru.senin.library.console.ConsoleApplicationRunner;
import ru.senin.library.console.ConsoleInputReader;
import ru.senin.library.console.ConsolePrinter;

public class LibraryApplication {

    public static void main(String[] args) {
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        BookCatalog bookCatalog = new BookCatalog();

        ConsoleBookHandler consoleBookHandler = new ConsoleBookHandler(
                consoleInputReader,
                consolePrinter,
                bookCatalog
        );

        ConsoleApplicationRunner consoleApplicationRunner = new ConsoleApplicationRunner(
                consoleInputReader,
                consolePrinter,
                consoleBookHandler
        );

        consoleApplicationRunner.run();

        // TODO [STAGE 3]:
        // Позже создание объектов приложения нужно будет перенести в более явную точку конфигурации.
    }
}
