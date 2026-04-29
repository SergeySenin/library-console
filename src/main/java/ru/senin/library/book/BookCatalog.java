package ru.senin.library.book;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * In-memory каталог книг и точка выполнения доменных операций над коллекцией.
 *
 * <p>Отвечает за хранение текущего набора книг, выдачу новых идентификаторов,
 * регистрацию, поиск, обновление и удаление записей.
 * Каталог работает уже с валидными и нормализованными значениями, а не с сырым пользовательским вводом.
 *
 * <p>Класс не парсит строки из консоли, не формулирует пользовательские сообщения и не раскрывает внутреннюю коллекцию.
 * Модель обновления строится вокруг неизменяемого {@link Book}:
 * старая запись заменяется новым объектом с тем же идентификатором.
 */
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

    public Optional<Book> findBookById(long bookId) {
        if (bookId <= 0) {
            throw new IllegalArgumentException("Book id must be greater than zero.");
        }

        for (Book book : books) {
            if (book.getId() == bookId) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    public List<Book> findBooksByTitle(String titleFragment) {
        String normalizedSearchQuery = validateSearchQuery(titleFragment).toLowerCase(Locale.ROOT);

        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            String normalizedBookTitle = book
                    .getTitle()
                    .toLowerCase(Locale.ROOT);

            if (normalizedBookTitle.contains(normalizedSearchQuery)) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    public List<Book> findBooksByAuthor(String authorFragment) {
        String normalizedSearchQuery = validateSearchQuery(authorFragment).toLowerCase(Locale.ROOT);

        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            String normalizedAuthorName = book
                    .getAuthorName()
                    .toLowerCase(Locale.ROOT);

            if (normalizedAuthorName.contains(normalizedSearchQuery)) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    public List<Book> findBooksByPublicationYear(Year publicationYear) {
        Objects.requireNonNull(
                publicationYear,
                "Publication year must not be null."
        );

        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getPublicationYear().equals(publicationYear)) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    public Optional<Book> updateBook(
            long bookId,
            String updatedTitle,
            String updatedAuthorName,
            Year updatedPublicationYear
    ) {
        if (bookId <= 0) {
            throw new IllegalArgumentException("Book id must be greater than zero.");
        }

        for (int index = 0; index < books.size(); index++) {
            Book book = books.get(index);

            if (book.getId() == bookId) {
                Book updatedBook = new Book(
                        bookId,
                        updatedTitle,
                        updatedAuthorName,
                        updatedPublicationYear
                );

                books.set(
                        index,
                        updatedBook
                );

                return Optional.of(updatedBook);
            }
        }

        return Optional.empty();
    }

    public Optional<Book> removeBook(long bookId) {
        if (bookId <= 0) {
            throw new IllegalArgumentException("Book id must be greater than zero.");
        }

        for (int index = 0; index < books.size(); index++) {
            Book book = books.get(index);

            if (book.getId() == bookId) {
                Book removedBook = books.remove(index);
                return Optional.of(removedBook);
            }
        }

        return Optional.empty();
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

        // TODO [STAGE 2]:
        // Демо-данные для текущего этапа разработки         (потенциально используются).
        // Книги должны загружаться из постоянного хранилища (временно не планируется).
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

    private String validateSearchQuery(String searchQuery) {
        Objects.requireNonNull(
                searchQuery,
                "Search query must not be null."
        );

        if (searchQuery.isBlank()) {
            throw new IllegalArgumentException("Search query must not be blank.");
        }

        return searchQuery;
    }

    // TODO [STAGE 3]:
    // Расширить каталог возможностями:
    // - проверки уникальности                                       (потенциально планируется);
    // - загрузки данных не из демо-метода, а из файлового хранилища (временно не планируется).
}
