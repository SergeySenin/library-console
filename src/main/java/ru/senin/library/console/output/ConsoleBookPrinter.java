package ru.senin.library.console.output;

import ru.senin.library.book.Book;

import java.time.Year;
import java.util.List;
import java.util.Objects;

public class ConsoleBookPrinter {

    public void printBookList(List<Book> books) {
        Objects.requireNonNull(
                books,
                "Book list must not be null."
        );

        System.out.println();
        System.out.println("================================= СПИСОК КНИГ ==================================");

        if (books.isEmpty()) {
            System.out.println("Каталог книг пуст: в системе пока нет зарегистрированных книг.");
            return;
        }

        for (Book book : books) {
            printBookDetails(book);
            printSeparatorLine();
        }

        // TODO [STAGE 7]:
        // Позже вывод каталога нужно улучшить:
        // - добавить нумерацию;
        // - добавить пагинацию;
        // - добавить сортировку;
        // - добавить краткий и полный режимы отображения.
    }

    public void printBookRegistrationHeader() {
        System.out.println();
        System.out.println("=========================== РЕГИСТРАЦИЯ НОВОЙ КНИГИ ============================");
    }

    public void printBookTitlePrompt() {
        System.out.print("Введите название книги: ");
    }

    public void printAuthorNamePrompt() {
        System.out.print("Введите имя автора:     ");
    }

    public void printPublicationYearPrompt() {
        System.out.print("Введите год издания:    ");
    }

    public void printBookRegisteredMessage(Book registeredBook) {
        Objects.requireNonNull(
                registeredBook,
                "Registered book must not be null."
        );

        System.out.println();
        System.out.println("Книга успешно зарегистрирована.");
        System.out.println("Создана запись:");
        printBookDetails(registeredBook);

        // TODO [STAGE 8]:
        // Позже сообщение об успешной регистрации можно расширить:
        // - указанием общего количества книг в каталоге;
        // - предложением перейти к просмотру каталога;
        // - автоматическим показом карточки книги в более полном формате.
    }

    public void printBookIdSearchHeader() {
        System.out.println();
        System.out.println("======================== ПОИСК КНИГИ ПО ИДЕНТИФИКАТОРУ =========================");
    }

    public void printBookIdPrompt() {
        System.out.print("Введите идентификатор книги: ");
    }

    public void printBookFoundById(
            long bookId,
            Book foundBook
    ) {
        Objects.requireNonNull(
                foundBook,
                "Found book must not be null."
        );

        printBookIdSearchResultHeader(bookId);
        printBookDetails(foundBook);
        printSeparatorLine();
    }

    public void printBookNotFoundById(long bookId) {
        printBookIdSearchResultHeader(bookId);
        System.out.println("Книга с таким идентификатором не найдена.");
    }

    public void printBookSearchHeader() {
        System.out.println();
        System.out.println("=========================== ПОИСК КНИГИ ПО НАЗВАНИЮ ============================");
    }

    public void printBookSearchPrompt() {
        System.out.print("Введите название книги или её часть: ");
    }

    public void printBookAuthorSearchHeader() {
        System.out.println();
        System.out.println("============================ ПОИСК КНИГИ ПО АВТОРУ =============================");
    }

    public void printBookAuthorSearchPrompt() {
        System.out.print("Введите имя автора или его часть: ");
    }

    public void printBookPublicationYearSearchHeader() {
        System.out.println();
        System.out.println("========================= ПОИСК КНИГИ ПО ГОДУ ИЗДАНИЯ ==========================");
    }

    public void printBookPublicationYearSearchPrompt() {
        System.out.print("Введите год издания: ");
    }

    public void printBookUpdateHeader() {
        System.out.println();
        System.out.println("============================= РЕДАКТИРОВАНИЕ КНИГИ =============================");
    }

    public void printBookUpdateHint() {
        System.out.println("Чтобы оставить текущее значение без изменений, нажмите Enter.");
    }

    public void printBookSelectedForUpdate(Book book) {
        Objects.requireNonNull(
                book,
                "Book must not be null."
        );

        System.out.println();
        System.out.println("Найдена книга для редактирования:");
        printBookDetails(book);
        printSeparatorLine();
    }

    public void printBookUpdateTitlePrompt(String currentTitle) {
        Objects.requireNonNull(
                currentTitle,
                "Current title must not be null."
        );

        System.out.print(
                "Новое название книги [текущее: "
                        + currentTitle
                        + "]: "
        );
    }

    public void printBookUpdateAuthorPrompt(String currentAuthorName) {
        Objects.requireNonNull(
                currentAuthorName,
                "Current author name must not be null."
        );

        System.out.print(
                "Новое имя автора [текущее: "
                        + currentAuthorName
                        + "]: "
        );
    }

    public void printBookUpdatePublicationYearPrompt(Year currentPublicationYear) {
        Objects.requireNonNull(
                currentPublicationYear,
                "Current publication year must not be null."
        );

        System.out.print(
                "Новый год издания [текущий: "
                        + currentPublicationYear
                        + "]: "
        );
    }

    public void printBookUpdatedMessage(Book updatedBook) {
        Objects.requireNonNull(
                updatedBook,
                "Updated book must not be null."
        );

        System.out.println();
        System.out.println("Книга успешно обновлена.");
        System.out.println("Обновлённая запись:");
        printBookDetails(updatedBook);

        // TODO [STAGE 16]:
        // Позже сообщение об успешном обновлении можно расширить:
        // - выводом перечня изменённых полей;
        // - логикой подтверждения изменений перед сохранением.
    }

    public void printBookSearchResults(
            String searchQuery,
            List<Book> foundBooks
    ) {
        Objects.requireNonNull(
                searchQuery,
                "Search query must not be null."
        );
        Objects.requireNonNull(
                foundBooks,
                "Found books list must not be null."
        );

        if (searchQuery.isBlank()) {
            throw new IllegalArgumentException("Search query must not be blank.");
        }

        System.out.println();
        System.out.println(
                "Результат поиска по запросу: \""
                        + searchQuery
                        + "\"."
        );

        if (foundBooks.isEmpty()) {
            System.out.println("По вашему запросу книги не найдены.");
            return;
        }

        System.out.println(
                "Найдено книг: "
                        + foundBooks.size()
        );
        printSeparatorLine();

        for (Book book : foundBooks) {
            printBookDetails(book);
            printSeparatorLine();
        }

        // TODO [STAGE 12]:
        // Позже результаты поиска можно улучшить:
        // - подсветкой совпавшего фрагмента;
        // - сортировкой результатов;
        // - унификацией вывода для разных видов поиска;
        // - переходом к карточке выбранной книги.
    }

    private void printBookDetails(Book book) {
        Objects.requireNonNull(
                book,
                "Book must not be null."
        );

        System.out.println(
                "Идентификатор: "
                        + book.getId()
        );
        System.out.println(
                "Название:      "
                        + book.getTitle()
        );
        System.out.println(
                "Автор:         "
                        + book.getAuthorName()
        );
        System.out.println(
                "Год издания:   "
                        + book.getPublicationYear()
        );
    }

    private void printSeparatorLine() {
        System.out.println("--------------------------------------------------------------------------------");
    }

    private void printBookIdSearchResultHeader(long bookId) {
        System.out.println();
        System.out.println(
                "Результат поиска по идентификатору: "
                        + bookId
                        + "."
        );
    }
}
