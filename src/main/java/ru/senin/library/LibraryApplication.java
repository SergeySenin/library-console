package ru.senin.library;

import ru.senin.library.book.BookCatalog;
import ru.senin.library.console.app.ConsoleApplicationRunner;
import ru.senin.library.console.command.ConsoleCommandRouter;
import ru.senin.library.console.handler.ConsoleBookHandler;
import ru.senin.library.console.input.ConsoleInputReader;
import ru.senin.library.console.output.ConsoleApplicationPrinter;
import ru.senin.library.console.output.ConsoleBookPrinter;
import ru.senin.library.console.validation.BookInputValidator;
import ru.senin.library.console.validation.MainMenuCommandValidator;

public class LibraryApplication {

    public static void main(String[] args) {
        ConsoleApplicationRunner consoleApplicationRunner = createConsoleApplicationRunner();
        consoleApplicationRunner.run();
    }

    private static ConsoleApplicationRunner createConsoleApplicationRunner() {
        BookCatalog bookCatalog = new BookCatalog();

        ConsoleInputReader consoleInputReader = new ConsoleInputReader();
        ConsoleApplicationPrinter consoleApplicationPrinter = new ConsoleApplicationPrinter();
        ConsoleBookPrinter consoleBookPrinter = new ConsoleBookPrinter();

        MainMenuCommandValidator mainMenuCommandValidator = new MainMenuCommandValidator();
        BookInputValidator bookInputValidator = new BookInputValidator();

        ConsoleBookHandler consoleBookHandler = new ConsoleBookHandler(
                consoleBookPrinter,
                consoleInputReader,
                bookInputValidator,
                consoleApplicationPrinter,
                bookCatalog
        );

        ConsoleCommandRouter consoleCommandRouter = new ConsoleCommandRouter(
                consoleApplicationPrinter,
                consoleBookHandler
        );

        return new ConsoleApplicationRunner(
                consoleApplicationPrinter,
                consoleInputReader,
                mainMenuCommandValidator,
                consoleCommandRouter
        );
    }

    // TODO [STAGE 1]:
    // Позже создание объектов приложения нужно будет перенести в отдельный bootstrap / configuration класс.
}
