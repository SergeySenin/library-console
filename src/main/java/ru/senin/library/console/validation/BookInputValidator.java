package ru.senin.library.console.validation;

import java.time.Year;
import java.util.Objects;

public class BookInputValidator {

    private static final int MAX_TITLE_LENGTH = 128;
    private static final int MAX_AUTHOR_NAME_LENGTH = 128;
    private static final int MAX_SEARCH_QUERY_LENGTH = 32;
    private static final int MIN_PUBLICATION_YEAR = 1500;

    public ValidationResult validateBookTitle(String title) {
        if (title == null || title.isBlank()) {
            return ValidationResult.invalid("Название книги не может быть пустым.");
        }

        String normalizedTitle = title.trim();

        if (hasNoLetterOrDigit(normalizedTitle)) {
            return ValidationResult.invalid(
                    "Название книги должно содержать хотя бы одну букву или цифру."
            );
        }

        if (!startsAndEndsWithLetterOrDigit(normalizedTitle)) {
            return ValidationResult.invalid(
                    "Название книги не должно начинаться или заканчиваться служебным символом."
            );
        }

        if (normalizedTitle.length() > MAX_TITLE_LENGTH) {
            return ValidationResult.invalid(
                    "Название книги слишком длинное. Максимум "
                            + MAX_TITLE_LENGTH
                            + " символов."
            );
        }

        return ValidationResult.valid();
    }

    public ValidationResult validateAuthorName(String authorName) {
        if (authorName == null || authorName.isBlank()) {
            return ValidationResult.invalid("Имя автора не может быть пустым.");
        }

        String normalizedAuthorName = authorName.trim();

        if (!containsOnlyAllowedAuthorCharacters(normalizedAuthorName)) {
            return ValidationResult.invalid(
                    "Имя автора может содержать только буквы, пробелы, дефис, апостроф и точку."
            );
        }

        if (!containsLetter(normalizedAuthorName)) {
            return ValidationResult.invalid("Имя автора должно содержать хотя бы одну букву.");
        }

        if (normalizedAuthorName.length() < 2) {
            return ValidationResult.invalid("Имя автора должно содержать минимум 2 символа.");
        }

        if (normalizedAuthorName.length() > MAX_AUTHOR_NAME_LENGTH) {
            return ValidationResult.invalid(
                    "Имя автора слишком длинное. Максимум "
                            + MAX_AUTHOR_NAME_LENGTH
                            + " символов."
            );
        }

        return ValidationResult.valid();
    }

    public ValidationResult validatePublicationYearText(String publicationYearText) {
        if (publicationYearText == null || publicationYearText.isBlank()) {
            return ValidationResult.invalid("Год издания не может быть пустым.");
        }

        String normalizedPublicationYearText = publicationYearText.trim();

        if (!normalizedPublicationYearText.matches("\\d{4}")) {
            return ValidationResult.invalid(
                    "Год издания должен состоять ровно из 4 цифр. Например: 2020."
            );
        }

        int publicationYearValue = Integer.parseInt(normalizedPublicationYearText);
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

        return Year.of(Integer.parseInt(publicationYearText.trim()));
    }

    public ValidationResult validateSearchQuery(String titleFragment) {
        if (titleFragment == null || titleFragment.isBlank()) {
            return ValidationResult.invalid("Поисковый запрос не может быть пустым.");
        }

        String normalizedTitleFragment = titleFragment.trim();

        if (normalizedTitleFragment.length() > MAX_SEARCH_QUERY_LENGTH) {
            return ValidationResult.invalid(
                    "Поисковый запрос слишком длинный. Максимум "
                            + MAX_SEARCH_QUERY_LENGTH
                            + " символов."
            );
        }

        if (hasNoLetterOrDigit(normalizedTitleFragment)) {
            return ValidationResult.invalid(
                    "Поисковый запрос должен содержать хотя бы одну букву или цифру."
            );
        }

        return ValidationResult.valid();
    }

    private boolean hasNoLetterOrDigit(String value) {
        for (char character : value.toCharArray()) {
            if (Character.isLetterOrDigit(character)) {
                return false;
            }
        }

        return true;
    }

    private boolean startsAndEndsWithLetterOrDigit(String value) {
        char firstCharacter = value.charAt(0);
        char lastCharacter = value.charAt(value.length() - 1);

        return Character.isLetterOrDigit(firstCharacter) && Character.isLetterOrDigit(lastCharacter);
    }

    private boolean containsLetter(String value) {
        for (char character : value.toCharArray()) {
            if (Character.isLetter(character)) {
                return true;
            }
        }

        return false;
    }

    private boolean containsOnlyAllowedAuthorCharacters(String value) {
        for (char character : value.toCharArray()) {
            if (Character.isLetter(character)
                    || character == ' '
                    || character == '-'
                    || character == '\''
                    || character == '.') {
                continue;
            }

            return false;
        }

        return true;
    }
}
