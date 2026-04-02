package ru.senin.library.console;

import ru.senin.library.book.Book;
import ru.senin.library.book.BookCatalog;

import java.time.Year;
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
                case "0":
                    consolePrinter.printApplicationFinishedMessage();
                    isApplicationRunning = false;
                    break;
                case "1":
                    showBookCatalog();
                    break;
                case "2":
                    registerNewBook();
                    break;
                case "3":
                    searchBooksByTitle();
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

    private void showBookCatalog() {
        List<Book> books = bookCatalog.getAllBooks();
        consolePrinter.printBookCatalog(books);
    }

    private void registerNewBook() {
        consolePrinter.printBookRegistrationHeader();

        consolePrinter.printBookTitlePrompt();
        String title = consoleInputReader.readRequiredText("Название книги");

        consolePrinter.printAuthorNamePrompt();
        String authorName = consoleInputReader.readRequiredText("Имя автора");

        consolePrinter.printPublicationYearPrompt();
        Year publicationYear = consoleInputReader.readPublicationYear();

        Book registeredBook = bookCatalog.registerBook(
                title,
                authorName,
                publicationYear
        );
        consolePrinter.printBookRegisteredMessage(registeredBook);
    }

    private void searchBooksByTitle() {
        consolePrinter.printBookSearchHeader();
        consolePrinter.printBookSearchPrompt();

        String titleFragment = consoleInputReader.readRequiredText("Поисковый запрос");
        List<Book> foundBooks = bookCatalog.searchBooksByTitleFragment(titleFragment);

        consolePrinter.printBookSearchResult(titleFragment, foundBooks);
    }
}
