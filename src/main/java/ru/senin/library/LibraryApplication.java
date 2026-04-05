package ru.senin.library;

import ru.senin.library.book.BookCatalog;
import ru.senin.library.console.ConsoleApplicationRunner;
import ru.senin.library.console.ConsoleBookHandler;
import ru.senin.library.console.ConsoleCommandRouter;
import ru.senin.library.console.ConsoleInputReader;
import ru.senin.library.console.ConsolePrinter;

public class LibraryApplication {

    public static void main(String[] args) {
        ConsoleApplicationRunner consoleApplicationRunner = createConsoleApplicationRunner();
        consoleApplicationRunner.run();
    }

    private static ConsoleApplicationRunner createConsoleApplicationRunner() {
        BookCatalog bookCatalog = new BookCatalog();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();

        ConsoleBookHandler consoleBookHandler = new ConsoleBookHandler(
                bookCatalog,
                consolePrinter,
                consoleInputReader
        );

        ConsoleCommandRouter consoleCommandRouter = new ConsoleCommandRouter(
                consolePrinter,
                consoleBookHandler
        );

        return new ConsoleApplicationRunner(
                consolePrinter,
                consoleInputReader,
                consoleCommandRouter
        );
    }

    // TODO [STAGE 3]:
    // Позже создание объектов приложения нужно будет перенести в более явную отдельную точку конфигурации.
}
