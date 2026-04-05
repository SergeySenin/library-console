package ru.senin.library.console;

import ru.senin.library.book.Book;
import ru.senin.library.book.BookCatalog;

import java.time.Year;
import java.util.List;

public class ConsoleBookHandler {

    private final BookCatalog bookCatalog;
    private final ConsoleBookPrinter consoleBookPrinter;
    private final ConsoleInputReader consoleInputReader;

    public ConsoleBookHandler(
            BookCatalog bookCatalog,
            ConsoleBookPrinter consoleBookPrinter,
            ConsoleInputReader consoleInputReader
    ) {
        this.bookCatalog = bookCatalog;
        this.consoleBookPrinter = consoleBookPrinter;
        this.consoleInputReader = consoleInputReader;
    }

    public void showAllBooks() {
        List<Book> books = bookCatalog.findAllBooks();
        consoleBookPrinter.printBookList(books);
    }

    public void registerNewBook() {
        consoleBookPrinter.printBookRegistrationHeader();

        consoleBookPrinter.printBookTitlePrompt();
        String title = consoleInputReader.readRequiredText("Название книги");

        consoleBookPrinter.printAuthorNamePrompt();
        String authorName = consoleInputReader.readRequiredText("Имя автора");

        consoleBookPrinter.printPublicationYearPrompt();
        Year publicationYear = consoleInputReader.readPublicationYear();

        Book registeredBook = bookCatalog.registerBook(
                title,
                authorName,
                publicationYear
        );

        consoleBookPrinter.printBookRegisteredMessage(registeredBook);
    }

    public void searchBooksByTitle() {
        consoleBookPrinter.printBookSearchHeader();
        consoleBookPrinter.printBookSearchPrompt();

        String titleFragment = consoleInputReader.readRequiredText("Поисковый запрос");
        List<Book> foundBooks = bookCatalog.findBooksByTitle(titleFragment);

        consoleBookPrinter.printBookSearchResults(titleFragment, foundBooks);
    }

    // TODO [STAGE 13]:
    // Позже этот обработчик нужно будет расширить:
    // - поиском книги по id;
    // - обновлением книги;
    // - удалением книги;
    // - (возможно) выделением отдельного подменю для операций с книгами.
}
