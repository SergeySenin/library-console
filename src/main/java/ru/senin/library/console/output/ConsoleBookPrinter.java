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
        // Улучшить вывод каталога:
        // - добавить нумерацию                           (временно не планируется);
        // - добавить пагинацию                           (временно не планируется);
        // - добавить сортировку                          (временно не планируется);
        // - добавить краткий и полный режимы отображения (временно не планируется).
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
        // Расширить сообщение об успешной регистрации:
        // - указанием общего количества книг в каталоге                  (потенциально планируется);
        // - предложением перейти к просмотру каталога                    (временно не планируется);
        // - автоматическим показом карточки книги в более полном формате (временно не планируется).
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

        // TODO [STAGE 9]:
        // Расширить сообщение об успешном обновлении:
        // - выводом перечня изменённых полей                  (временно не планируется);
        // - логикой подтверждения изменений перед сохранением (потенциально планируется).
    }

    public void printBookRemovalHeader() {
        System.out.println();
        System.out.println("================================ УДАЛЕНИЕ КНИГИ ================================");
    }

    public void printBookSelectedForRemoval(Book book) {
        Objects.requireNonNull(
                book,
                "Book must not be null."
        );

        System.out.println();
        System.out.println("Найдена книга для удаления:");
        printBookDetails(book);
        printSeparatorLine();
    }

    public void printBookRemovalConfirmationPrompt() {
        System.out.print("Введите Да для подтверждения удаления или Нет для отмены: ");
    }

    public void printBookRemovedMessage(Book removedBook) {
        Objects.requireNonNull(
                removedBook,
                "Removed book must not be null."
        );

        System.out.println();
        System.out.println("Книга успешно удалена.");
        System.out.println("Удалённая запись:");
        printBookDetails(removedBook);

        // TODO [STAGE 10]:
        // Расширить сообщение об успешном удалении:
        // - указанием оставшегося количества книг в каталоге                   (потенциально планируется);
        // - предупреждением о невозможности восстановления без резервной копии (временно не планируется);
        // - логикой дополнительного подтверждения для критичных операций       (временно не планируется).
    }

    public void printBookRemovalCanceledMessage() {
        System.out.println();
        System.out.println("Удаление книги отменено пользователем.");
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

        // TODO [STAGE 11]:
        // Улучшить результаты поиска:
        // - подсветкой совпавшего фрагмента            (временно не планируется);
        // - сортировкой результатов                    (временно не планируется);
        // - переходом к карточке выбранной книги       (временно не планируется).
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
