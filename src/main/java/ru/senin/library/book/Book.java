package ru.senin.library.book;

import java.time.Year;

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
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.publicationYear = publicationYear;
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

    // TODO [STAGE 9]:
    // Позже сущность Book нужно будет расширить:
    // - ISBN;
    // - жанром;
    // - описанием;
    // - издательством;
    // - количеством экземпляров через отдельную сущность BookCopy;
    // - правилами валидации обязательных полей.
}
