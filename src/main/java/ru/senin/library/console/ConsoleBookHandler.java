package ru.senin.library.console;

import ru.senin.library.book.Book;
import ru.senin.library.book.BookCatalog;

import java.time.Year;
import java.util.List;

public class ConsoleBookHandler {

    private final BookCatalog bookCatalog;
    private final ConsolePrinter consolePrinter;
    private final ConsoleInputReader consoleInputReader;

    public ConsoleBookHandler(
            BookCatalog bookCatalog,
            ConsolePrinter consolePrinter,
            ConsoleInputReader consoleInputReader
    ) {
        this.bookCatalog = bookCatalog;
        this.consolePrinter = consolePrinter;
        this.consoleInputReader = consoleInputReader;
    }

    public void showAllBooks() {
        List<Book> books = bookCatalog.findAllBooks();
        consolePrinter.printBookList(books);
    }

    public void registerNewBook() {
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

    public void searchBooksByTitle() {
        consolePrinter.printBookSearchHeader();
        consolePrinter.printBookSearchPrompt();

        String titleFragment = consoleInputReader.readRequiredText("Поисковый запрос");
        List<Book> foundBooks = bookCatalog.findBooksByTitle(titleFragment);

        consolePrinter.printBookSearchResults(titleFragment, foundBooks);
    }

    // TODO [STAGE 13]:
    // Позже этот обработчик нужно будет расширить:
    // - поиском книги по id;
    // - обновлением книги;
    // - удалением книги;
    // - (возможно) выделением отдельного подменю для операций с книгами.
}
