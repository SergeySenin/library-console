package ru.senin.library.book;

import java.time.Year;
import java.util.Objects;

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
            String value,
            String fieldName
    ) {
        Objects.requireNonNull(
                value,
                fieldName
                        + " must not be null."
        );

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName
                            + " must not be blank."
            );
        }

        return value;
    }

    // TODO [STAGE 9]:
    // Позже сущность Book нужно будет расширить:
    // - ISBN;
    // - жанром;
    // - описанием;
    // - издательством;
    // - количеством экземпляров через отдельную сущность BookCopy;
    // - правилами валидации обязательных полей.
}
