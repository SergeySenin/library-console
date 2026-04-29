package ru.senin.library.book;

import java.time.Year;
import java.util.Objects;

/**
 * Доменная сущность книги с минимальными инвариантами состояния.
 *
 * <p>Хранит идентификатор книги, название, имя автора и год издания.
 * Экземпляр создаётся только в корректном состоянии и после создания не изменяется.
 *
 * <p>Класс не является DTO, не содержит UI-логики и не знает ничего о консольных сценариях приложения.
 * Обновление книги в системе выполняется через создание новой версии объекта и замену записи в {@link BookCatalog}.
 */
public class Book {

    private final long id;
    private final String title;
    private final String authorName;
    private final Year publicationYear;

    public Book(
            long id,
            String title,
            String authorName,
            Year publicationYear
    ) {
        this.id = validateId(id);
        this.title = validateRequiredText(
                title,
                "Book title"
        );
        this.authorName = validateRequiredText(
                authorName,
                "Author name"
        );
        this.publicationYear = Objects.requireNonNull(
                publicationYear,
                "Publication year must not be null."
        );
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    private static long validateId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Book id must be greater than zero.");
        }

        return id;
    }

    private static String validateRequiredText(
            String textValue,
            String fieldName
    ) {
        Objects.requireNonNull(
                textValue,
                fieldName
                        + " must not be null."
        );

        if (textValue.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName
                            + " must not be blank."
            );
        }

        return textValue;
    }

    // TODO [STAGE 1]:
    // Расширить сущность Book:
    // - жанром                                                    (потенциально планируется);
    // - описанием                                                 (потенциально планируется);
    // - ISBN                                                      (потенциально планируется);
    // - издательством                                             (потенциально планируется);
    // - количеством экземпляров через отдельную сущность BookCopy (потенциально планируется);
    // - правилами валидации обязательных полей                    (временно не планируется).
}
