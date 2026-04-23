package ru.senin.library.console.handler;

import ru.senin.library.book.Book;
import ru.senin.library.book.BookCatalog;
import ru.senin.library.console.input.ConsoleInputReader;
import ru.senin.library.console.output.ConsoleApplicationPrinter;
import ru.senin.library.console.output.ConsoleBookPrinter;
import ru.senin.library.console.validation.BookInputValidator;

import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ConsoleBookHandler {

    private final ConsoleBookPrinter consoleBookPrinter;
    private final ConsoleInputReader consoleInputReader;
    private final BookInputValidator bookInputValidator;
    private final ConsoleApplicationPrinter consoleApplicationPrinter;
    private final BookCatalog bookCatalog;

    public ConsoleBookHandler(
            ConsoleBookPrinter consoleBookPrinter,
            ConsoleInputReader consoleInputReader,
            BookInputValidator bookInputValidator,
            ConsoleApplicationPrinter consoleApplicationPrinter,
            BookCatalog bookCatalog
    ) {
        this.consoleBookPrinter = Objects.requireNonNull(
                consoleBookPrinter,
                "Console book printer must not be null."
        );
        this.consoleInputReader = Objects.requireNonNull(
                consoleInputReader,
                "Console input reader must not be null."
        );
        this.bookInputValidator = Objects.requireNonNull(
                bookInputValidator,
                "Book input validator must not be null."
        );
        this.consoleApplicationPrinter = Objects.requireNonNull(
                consoleApplicationPrinter,
                "Console application printer must not be null."
        );
        this.bookCatalog = Objects.requireNonNull(
                bookCatalog,
                "Book catalog must not be null."
        );
    }

    public void showAllBooks() {
        List<Book> books = bookCatalog.findAllBooks();
        consoleBookPrinter.printBookList(books);
    }

    public void registerNewBook() {
        consoleBookPrinter.printBookRegistrationHeader();

        consoleBookPrinter.printBookTitlePrompt();
        String title = consoleInputReader.readValidatedLine(
                bookInputValidator::validateBookTitle,
                consoleApplicationPrinter::printValidationError
        );

        consoleBookPrinter.printAuthorNamePrompt();
        String authorName = consoleInputReader.readValidatedLine(
                bookInputValidator::validateAuthorName,
                consoleApplicationPrinter::printValidationError
        );

        consoleBookPrinter.printPublicationYearPrompt();
        String publicationYearText = consoleInputReader.readValidatedLine(
                bookInputValidator::validatePublicationYearText,
                consoleApplicationPrinter::printValidationError
        );

        Year publicationYear = bookInputValidator.parsePublicationYear(publicationYearText);

        Book registeredBook = bookCatalog.registerBook(
                title,
                authorName,
                publicationYear
        );

        consoleBookPrinter.printBookRegisteredMessage(registeredBook);
    }

    public void searchBookById() {
        consoleBookPrinter.printBookIdSearchHeader();
        consoleBookPrinter.printBookIdPrompt();

        String bookIdText = consoleInputReader.readValidatedLine(
                bookInputValidator::validateBookIdText,
                consoleApplicationPrinter::printValidationError
        );

        long bookId = bookInputValidator.parseBookId(bookIdText);
        Optional<Book> foundBook = bookCatalog.findBookById(bookId);

        if (foundBook.isPresent()) {
            consoleBookPrinter.printBookFoundById(
                    bookId,
                    foundBook.get()
            );
            return;
        }

        consoleBookPrinter.printBookNotFoundById(bookId);
    }

    public void searchBooksByTitle() {
        consoleBookPrinter.printBookSearchHeader();
        consoleBookPrinter.printBookSearchPrompt();

        String titleSearchQuery = consoleInputReader.readValidatedLine(
                bookInputValidator::validateTitleSearchQuery,
                consoleApplicationPrinter::printValidationError
        );

        List<Book> foundBooks = bookCatalog.findBooksByTitle(titleSearchQuery);

        consoleBookPrinter.printBookSearchResults(
                titleSearchQuery,
                foundBooks
        );
    }

    public void searchBooksByAuthor() {
        consoleBookPrinter.printBookAuthorSearchHeader();
        consoleBookPrinter.printBookAuthorSearchPrompt();

        String authorSearchQuery = consoleInputReader.readValidatedLine(
                bookInputValidator::validateAuthorSearchQuery,
                consoleApplicationPrinter::printValidationError
        );

        List<Book> foundBooks = bookCatalog.findBooksByAuthor(authorSearchQuery);

        consoleBookPrinter.printBookSearchResults(
                authorSearchQuery,
                foundBooks
        );
    }

    public void searchBooksByPublicationYear() {
        consoleBookPrinter.printBookPublicationYearSearchHeader();
        consoleBookPrinter.printBookPublicationYearSearchPrompt();

        String publicationYearSearchQuery = consoleInputReader.readValidatedLine(
                bookInputValidator::validatePublicationYearSearchQuery,
                consoleApplicationPrinter::printValidationError
        );

        Year publicationYear = bookInputValidator.parsePublicationYearSearchQuery(publicationYearSearchQuery);
        List<Book> foundBooks = bookCatalog.findBooksByPublicationYear(publicationYear);

        consoleBookPrinter.printBookSearchResults(
                publicationYearSearchQuery,
                foundBooks
        );
    }

    public void updateBook() {
        consoleBookPrinter.printBookUpdateHeader();

        long bookId = readBookIdForUpdate();
        Optional<Book> foundBook = bookCatalog.findBookById(bookId);

        if (foundBook.isEmpty()) {
            consoleBookPrinter.printBookNotFoundById(bookId);
            return;
        }

        Book bookToUpdate = foundBook.get();
        Book updatedBookCandidate = buildUpdatedBookCandidate(bookToUpdate);

        Optional<Book> updatedBook = bookCatalog.updateBook(
                bookId,
                updatedBookCandidate.getTitle(),
                updatedBookCandidate.getAuthorName(),
                updatedBookCandidate.getPublicationYear()
        );

        if (updatedBook.isPresent()) {
            consoleBookPrinter.printBookUpdatedMessage(updatedBook.get());
            return;
        }

        consoleBookPrinter.printBookNotFoundById(bookId);
    }

    private long readBookIdForUpdate() {
        consoleBookPrinter.printBookIdPrompt();

        String bookIdText = consoleInputReader.readValidatedLine(
                bookInputValidator::validateBookIdText,
                consoleApplicationPrinter::printValidationError
        );

        return bookInputValidator.parseBookId(bookIdText);
    }

    private Book buildUpdatedBookCandidate(Book bookToUpdate) {
        consoleBookPrinter.printBookSelectedForUpdate(bookToUpdate);
        consoleBookPrinter.printBookUpdateHint();

        consoleBookPrinter.printBookUpdateTitlePrompt(bookToUpdate.getTitle());
        String updatedTitleInput = consoleInputReader.readOptionalValidatedLine(
                bookInputValidator::validateBookTitle,
                consoleApplicationPrinter::printValidationError
        );

        consoleBookPrinter.printBookUpdateAuthorPrompt(bookToUpdate.getAuthorName());
        String updatedAuthorInput = consoleInputReader.readOptionalValidatedLine(
                bookInputValidator::validateAuthorName,
                consoleApplicationPrinter::printValidationError
        );

        consoleBookPrinter.printBookUpdatePublicationYearPrompt(bookToUpdate.getPublicationYear());
        String updatedPublicationYearInput = consoleInputReader.readOptionalValidatedLine(
                bookInputValidator::validatePublicationYearText,
                consoleApplicationPrinter::printValidationError
        );

        String updatedTitle = updatedTitleInput.isEmpty()
                ? bookToUpdate.getTitle()
                : updatedTitleInput;

        String updatedAuthorName = updatedAuthorInput.isEmpty()
                ? bookToUpdate.getAuthorName()
                : updatedAuthorInput;

        Year updatedPublicationYear = updatedPublicationYearInput.isEmpty()
                ? bookToUpdate.getPublicationYear()
                : bookInputValidator.parsePublicationYear(updatedPublicationYearInput);

        return new Book(
                bookToUpdate.getId(),
                updatedTitle,
                updatedAuthorName,
                updatedPublicationYear
        );
    }

    // TODO [STAGE 17]:
    // Позже этот обработчик нужно будет расширить:
    // - удалением книги;
    // - (возможно) выделением отдельного подменю для операций с книгами.
}
