package ru.senin.library;

import ru.senin.library.book.BookCatalog;
import ru.senin.library.console.ConsoleApplicationPrinter;
import ru.senin.library.console.ConsoleApplicationRunner;
import ru.senin.library.console.ConsoleBookHandler;
import ru.senin.library.console.ConsoleBookPrinter;
import ru.senin.library.console.ConsoleCommandRouter;
import ru.senin.library.console.ConsoleInputReader;

public class LibraryApplication {

    public static void main(String[] args) {
        ConsoleApplicationRunner consoleApplicationRunner = createConsoleApplicationRunner();
        consoleApplicationRunner.run();
    }

    private static ConsoleApplicationRunner createConsoleApplicationRunner() {
        BookCatalog bookCatalog = new BookCatalog();
        ConsoleApplicationPrinter consoleApplicationPrinter = new ConsoleApplicationPrinter();
        ConsoleBookPrinter consoleBookPrinter = new ConsoleBookPrinter();
        ConsoleInputReader consoleInputReader = new ConsoleInputReader();

        ConsoleBookHandler consoleBookHandler = new ConsoleBookHandler(
                bookCatalog,
                consoleBookPrinter,
                consoleInputReader
        );

        ConsoleCommandRouter consoleCommandRouter = new ConsoleCommandRouter(
                consoleApplicationPrinter,
                consoleBookHandler
        );

        return new ConsoleApplicationRunner(
                consoleApplicationPrinter,
                consoleInputReader,
                consoleCommandRouter
        );
    }

    // TODO [STAGE 3]:
    // Позже создание объектов приложения нужно будет перенести в более явную отдельную точку конфигурации.
}
