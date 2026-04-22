package ru.senin.library.console.validation;

import java.time.Year;
import java.util.Objects;

public class BookInputValidator {

    private static final int MAX_TITLE_LENGTH = 128;
    private static final int MAX_AUTHOR_NAME_LENGTH = 128;
    private static final int MAX_TITLE_SEARCH_QUERY_LENGTH = 32;
    private static final int MAX_AUTHOR_SEARCH_QUERY_LENGTH = 32;
    private static final int MIN_PUBLICATION_YEAR = 1500;

    public ValidationResult validateBookIdText(String bookIdText) {
        if (bookIdText == null) {
            return ValidationResult.invalid("Идентификатор книги " +
                    "не может быть пустым.");
        }

        if (bookIdText.isBlank()) {
            return ValidationResult.invalid("Идентификатор книги " +
                    "не может быть пустым.");
        }

        if (containsNonDigit(bookIdText)) {
            return ValidationResult.invalid("Идентификатор книги " +
                    "должен содержать только положительное целое число.");
        }

        try {
            long bookId = Long.parseLong(bookIdText);

            if (bookId <= 0) {
                return ValidationResult.invalid("Идентификатор книги " +
                        "должен быть больше нуля.");
            }
        } catch (NumberFormatException exception) {
            return ValidationResult.invalid("Идентификатор книги " +
                    "слишком большой.");
        }

        return ValidationResult.valid();
    }

    public long parseBookId(String bookIdText) {
        Objects.requireNonNull(
                bookIdText,
                "Book id text must not be null."
        );

        return Long.parseLong(bookIdText);
    }

    public ValidationResult validateBookTitle(String bookTitle) {
        if (bookTitle == null) {
            return ValidationResult.invalid("Название книги " +
                    "не может быть пустым.");
        }

        if (bookTitle.isBlank()) {
            return ValidationResult.invalid("Название книги " +
                    "не может быть пустым.");
        }

        if (bookTitle.length() > MAX_TITLE_LENGTH) {
            return ValidationResult.invalid(
                    "Название книги слишком длинное. Максимум "
                            + MAX_TITLE_LENGTH
                            + " символов."
            );
        }

        if (startsOrEndsWithNonLetterOrDigit(bookTitle)) {
            return ValidationResult.invalid("Название книги " +
                    "не должно начинаться или заканчиваться служебным символом.");
        }

        if (hasNoLetterOrDigit(bookTitle)) {
            return ValidationResult.invalid("Название книги " +
                    "должно содержать хотя бы одну букву или цифру.");
        }

        return ValidationResult.valid();
    }

    public ValidationResult validateAuthorName(String authorName) {
        if (authorName == null) {
            return ValidationResult.invalid("Имя автора " +
                    "не может быть пустым.");
        }

        if (authorName.isBlank()) {
            return ValidationResult.invalid("Имя автора " +
                    "не может быть пустым.");
        }

        if (authorName.length() > MAX_AUTHOR_NAME_LENGTH) {
            return ValidationResult.invalid(
                    "Имя автора слишком длинное. Максимум "
                            + MAX_AUTHOR_NAME_LENGTH
                            + " символов."
            );
        }

        if (containsDisallowedAuthorCharacters(authorName)) {
            return ValidationResult.invalid("Имя автора " +
                    "может содержать только буквы, пробелы, дефис, апостроф и точку.");
        }

        if (hasNoLetter(authorName)) {
            return ValidationResult.invalid("Имя автора " +
                    "должно содержать хотя бы одну букву.");
        }

        if (authorName.length() < 2) {
            return ValidationResult.invalid("Имя автора " +
                    "должно содержать минимум 2 символа.");
        }

        return ValidationResult.valid();
    }

    public ValidationResult validatePublicationYearText(String publicationYearText) {
        if (publicationYearText == null) {
            return ValidationResult.invalid("Год издания " +
                    "не может быть пустым.");
        }

        if (publicationYearText.isBlank()) {
            return ValidationResult.invalid("Год издания " +
                    "не может быть пустым.");
        }

        if (!publicationYearText.matches("\\d{4}")) {
            return ValidationResult.invalid("Год издания " +
                    "должен состоять ровно из 4 цифр. Например: 2020.");
        }

        int publicationYearValue = Integer.parseInt(publicationYearText);
        int currentYear = Year.now().getValue();

        if (publicationYearValue < MIN_PUBLICATION_YEAR || publicationYearValue > currentYear) {
            return ValidationResult.invalid(
                    "Год издания должен быть в диапазоне от "
                            + MIN_PUBLICATION_YEAR
                            + " до "
                            + currentYear
                            + "."
            );
        }

        return ValidationResult.valid();
    }

    public Year parsePublicationYear(String publicationYearText) {
        Objects.requireNonNull(
                publicationYearText,
                "Publication year text must not be null."
        );

        return Year.of(Integer.parseInt(publicationYearText));
    }

    public ValidationResult validateTitleSearchQuery(String titleSearchQuery) {
        if (titleSearchQuery == null) {
            return ValidationResult.invalid("Поисковый запрос " +
                    "не может быть пустым.");
        }

        if (titleSearchQuery.isBlank()) {
            return ValidationResult.invalid("Поисковый запрос " +
                    "не может быть пустым.");
        }

        if (titleSearchQuery.length() > MAX_TITLE_SEARCH_QUERY_LENGTH) {
            return ValidationResult.invalid(
                    "Поисковый запрос слишком длинный. Максимум "
                            + MAX_TITLE_SEARCH_QUERY_LENGTH
                            + " символов."
            );
        }

        if (hasNoLetterOrDigit(titleSearchQuery)) {
            return ValidationResult.invalid("Поисковый запрос " +
                    "должен содержать хотя бы одну букву или цифру.");
        }

        return ValidationResult.valid();
    }

    public ValidationResult validateAuthorSearchQuery(String authorSearchQuery) {
        if (authorSearchQuery == null) {
            return ValidationResult.invalid("Поисковый запрос по автору " +
                    "не может быть пустым.");
        }

        if (authorSearchQuery.isBlank()) {
            return ValidationResult.invalid("Поисковый запрос по автору " +
                    "не может быть пустым.");
        }

        if (authorSearchQuery.length() > MAX_AUTHOR_SEARCH_QUERY_LENGTH) {
            return ValidationResult.invalid(
                    "Поисковый запрос по автору слишком длинный. Максимум "
                            + MAX_AUTHOR_SEARCH_QUERY_LENGTH
                            + " символов."
            );
        }

        if (containsDisallowedAuthorCharacters(authorSearchQuery)) {
            return ValidationResult.invalid("Поисковый запрос по автору " +
                    "может содержать только буквы, пробелы, дефис, апостроф и точку.");
        }

        if (hasNoLetter(authorSearchQuery)) {
            return ValidationResult.invalid("Поисковый запрос по автору " +
                    "должен содержать хотя бы одну букву.");
        }

        return ValidationResult.valid();
    }

    private boolean containsNonDigit(String value) {
        for (char character : value.toCharArray()) {
            if (!Character.isDigit(character)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasNoLetterOrDigit(String value) {
        for (char character : value.toCharArray()) {
            if (Character.isLetterOrDigit(character)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasNoLetter(String value) {
        for (char character : value.toCharArray()) {
            if (Character.isLetter(character)) {
                return false;
            }
        }

        return true;
    }

    private boolean startsOrEndsWithNonLetterOrDigit(String value) {
        char firstCharacter = value.charAt(0);
        char lastCharacter = value.charAt(value.length() - 1);

        return !Character.isLetterOrDigit(firstCharacter) || !Character.isLetterOrDigit(lastCharacter);
    }

    private boolean containsDisallowedAuthorCharacters(String value) {
        for (char character : value.toCharArray()) {
            if (Character.isLetter(character)
                    || character == ' '
                    || character == '-'
                    || character == '\''
                    || character == '.') {
                continue;
            }

            return true;
        }

        return false;
    }
}
