package ru.senin.library.console;

import ru.senin.library.book.Book;

import java.util.List;

public class ConsoleBookPrinter {

    public void printBookList(List<Book> books) {
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

    public void printBookSearchHeader() {
        System.out.println();
        System.out.println("=========================== ПОИСК КНИГИ ПО НАЗВАНИЮ ============================");
    }

    public void printBookSearchPrompt() {
        System.out.print("Введите название книги или её часть: ");
    }

    public void printBookSearchResults(
            String titleFragment,
            List<Book> foundBooks
    ) {
        System.out.println();
        System.out.println(
                "Результат поиска по запросу: \""
                        + titleFragment
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

        // TODO [STAGE 9]:
        // Позже результаты поиска можно улучшить:
        // - подсветкой совпавшего фрагмента;
        // - поиском не только по названию, но и по автору;
        // - сортировкой результатов;
        // - переходом к карточке выбранной книги.
    }

    private void printBookDetails(Book book) {
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
}
