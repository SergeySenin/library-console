package ru.senin.library.console;

import ru.senin.library.book.Book;
import ru.senin.library.book.BookCatalog;

import java.util.List;

public class ConsoleApplicationRunner {

    private final ConsoleInputReader consoleInputReader;
    private final ConsolePrinter consolePrinter;
    private final BookCatalog bookCatalog;

    public ConsoleApplicationRunner(
            ConsoleInputReader consoleInputReader,
            ConsolePrinter consolePrinter,
            BookCatalog bookCatalog
    ) {
        this.consoleInputReader = consoleInputReader;
        this.consolePrinter = consolePrinter;
        this.bookCatalog = bookCatalog;
    }

    public void run() {
        boolean isApplicationRunning = true;

        // TODO [STAGE 1]:
        // Это стартовый runner консольного приложения.
        // Позже отсюда нужно будет вынести:
        // 1. маршрутизацию команд в отдельный handler;
        // 2. регистрацию команд в отдельную структуру;
        // 3. переходы между вложенными меню.

        consolePrinter.printApplicationHeader();

        while (isApplicationRunning) {
            consolePrinter.printMainMenu();
            String userCommand = consoleInputReader.readCommand();

            switch (userCommand) {
                case "1":
                    List<Book> books = bookCatalog.getAllBooks();
                    consolePrinter.printBookCatalog(books);
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
