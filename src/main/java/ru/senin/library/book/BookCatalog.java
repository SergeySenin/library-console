package ru.senin.library.book;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class BookCatalog {

    private final List<Book> books;

    public BookCatalog() {
        this.books = new ArrayList<>();
        loadDemoBooks();
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    private void loadDemoBooks() {
        books.add(new Book(1L, "Clean Code", "Robert C. Martin", Year.of(2008)));
        books.add(new Book(2L, "Effective Java", "Joshua Bloch", Year.of(2018)));
        books.add(new Book(3L, "Head First Java", "Kathy Sierra", Year.of(2005)));
        books.add(new Book(4L, "Java Concurrency in Practice", "Brian Goetz", Year.of(2006)));
        books.add(new Book(5L, "Refactoring", "Martin Fowler", Year.of(2018)));

        // TODO [STAGE 10]:
        // Демо-данные нужны только для текущего этапа разработки.
        // Позже книги должны загружаться из постоянного хранилища.
    }

    // TODO [STAGE 11]:
    // Позже каталог нужно будет расширить возможностями:
    // - добавления книги;
    // - поиска книги по id;
    // - поиска по названию;
    // - проверки уникальности;
    // - загрузки данных не из демо-метода, а из файлового хранилища.
}
