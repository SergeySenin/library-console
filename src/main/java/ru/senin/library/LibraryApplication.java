package ru.senin.library;

import ru.senin.library.book.BookCatalog;
import ru.senin.library.console.output.ConsoleApplicationPrinter;
import ru.senin.library.console.app.ConsoleApplicationRunner;
import ru.senin.library.console.handler.ConsoleBookHandler;
import ru.senin.library.console.output.ConsoleBookPrinter;
import ru.senin.library.console.command.ConsoleCommandRouter;
import ru.senin.library.console.input.ConsoleInputReader;

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
