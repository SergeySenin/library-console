package ru.senin.library;

import ru.senin.library.book.BookCatalog;
import ru.senin.library.console.app.ConsoleApplicationRunner;
import ru.senin.library.console.command.ConsoleCommandRouter;
import ru.senin.library.console.handler.ConsoleBookHandler;
import ru.senin.library.console.input.ConsoleInputReader;
import ru.senin.library.console.output.ConsoleApplicationPrinter;
import ru.senin.library.console.output.ConsoleBookPrinter;
import ru.senin.library.console.validation.BookInputValidator;
import ru.senin.library.console.validation.ConfirmationAnswerValidator;
import ru.senin.library.console.validation.MainMenuCommandValidator;

/**
 * Точка входа и текущий composition root консольного приложения.
 *
 * <p>Собирает граф зависимостей приложения вручную, создаёт {@link ConsoleApplicationRunner} и передаёт ему управление.
 * В одном месте компонуются доменные, validation-, input-, output-, handler- и command-компоненты.
 *
 * <p>Класс не содержит предметной логики, маршрутизации команд, чтения ввода и прямого консольного UI.
 * Его ответственность ограничена сборкой приложения и запуском верхнеуровневого runner-а.
 */
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
        ConfirmationAnswerValidator confirmationAnswerValidator = new ConfirmationAnswerValidator();

        ConsoleBookHandler consoleBookHandler = new ConsoleBookHandler(
                consoleBookPrinter,
                consoleInputReader,
                bookInputValidator,
                confirmationAnswerValidator,
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

    // TODO [STAGE 16]:
    // Перенести создание объектов приложения в отдельный bootstrap / configuration класс (временно не планируется).
}
