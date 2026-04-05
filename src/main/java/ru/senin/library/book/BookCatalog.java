package ru.senin.library.book;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class BookCatalog {

    private final List<Book> books;
    private long nextBookId;

    public BookCatalog() {
        this.books = new ArrayList<>();
        this.nextBookId = 1L;

        loadDemoBooks();
    }

    public List<Book> findAllBooks() {
        return new ArrayList<>(books);
    }

    public Book registerBook(
            String title,
            String authorName,
            Year publicationYear
    ) {
        Book registeredBook = new Book(
                nextBookId,
                title,
                authorName,
                publicationYear
        );

        books.add(registeredBook);
        nextBookId++;

        return registeredBook;
    }

    public List<Book> findBooksByTitle(String titleFragment) {
        String normalizedTitleFragment = validateSearchQuery(titleFragment).toLowerCase(Locale.ROOT);

        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            String normalizedBookTitle = book
                    .getTitle()
                    .toLowerCase(Locale.ROOT);

            if (normalizedBookTitle.contains(normalizedTitleFragment)) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    private void loadDemoBooks() {
        addDemoBook(
                "Clean Code",
                "Robert C. Martin",
                Year.of(2008)
        );

        addDemoBook(
                "Effective Java",
                "Joshua Bloch",
                Year.of(2018)
        );

        addDemoBook(
                "Head First Java",
                "Kathy Sierra",
                Year.of(2005)
        );

        addDemoBook(
                "Java Concurrency in Practice",
                "Brian Goetz",
                Year.of(2006)
        );

        addDemoBook(
                "Refactoring",
                "Martin Fowler",
                Year.of(2018)
        );

        // TODO [STAGE 10]:
        // Демо-данные нужны только для текущего этапа разработки.
        // Позже книги должны загружаться из постоянного хранилища.
    }

    private void addDemoBook(
            String title,
            String authorName,
            Year publicationYear
    ) {
        books.add(
                new Book(
                        nextBookId,
                        title,
                        authorName,
                        publicationYear
                )
        );

        nextBookId++;
    }

    private String validateSearchQuery(String titleFragment) {
        Objects.requireNonNull(
                titleFragment,
                "Title fragment must not be null."
        );

        if (titleFragment.isBlank()) {
            throw new IllegalArgumentException("Title fragment must not be blank.");
        }

        return titleFragment;
    }

    // TODO [STAGE 11]:
    // Позже каталог нужно будет расширить возможностями:
    // - поиска книги по id;
    // - проверки уникальности;
    // - обновления книги;
    // - удаления книги;
    // - загрузки данных не из демо-метода, а из файлового хранилища.
}
