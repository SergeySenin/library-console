package ru.senin.library.console;

import ru.senin.library.book.Book;

import java.util.List;

public class ConsolePrinter {

    public void printApplicationHeader() {
        System.out.println();
        System.out.println("================================================================================");
        System.out.println("                     УЧЕБНАЯ СИСТЕМА УПРАВЛЕНИЯ БИБЛИОТЕКОЙ                     ");
        System.out.println("================================================================================");
        System.out.println("Текущая версия: начальный консольный каркас приложения.");
        System.out.println("Назначение этапа: проверить запуск, цикл меню и обработку команд.");

        // TODO [STAGE 5]:
        // Позже стартовый экран можно расширить:
        // - текущей датой и временем;
        // - информацией о версии приложения;
        // - сообщением о загрузке данных из файлового хранилища;
        // - количеством книг, экземпляров и читателей в системе.
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("--------------------------------- ГЛАВНОЕ МЕНЮ ---------------------------------");
        System.out.println("0 - Завершить работу приложения");
        System.out.println("1 - Показать все книги, зарегистрированные в каталоге");
        System.out.println("2 - Зарегистрировать новую книгу");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Введите номер команды и нажмите Enter: ");

        // TODO [STAGE 6]:
        // Позже главное меню будет заменено на полноценную навигацию:
        // 1. Книги
        // 2. Читатели
        // 3. Выдачи и возвраты
        // 4. Отчёты
        // 5. Настройки
        // 0. Выход
    }

    public void printBookCatalog(List<Book> books) {
        System.out.println();
        System.out.println("================================= СПИСОК КНИГ ==================================");

        if (books.isEmpty()) {
            System.out.println("Каталог книг пуст: в системе пока нет зарегистрированных книг.");
            return;
        }

        for (Book book : books) {
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
            System.out.println("--------------------------------------------------------------------------------");
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
        System.out.println("Книга успешно зарегистрирована в каталоге.");
        System.out.println("Создана запись:");
        System.out.println(
                "Идентификатор книги: "
                        + registeredBook.getId()
        );
        System.out.println(
                "Название книги:      "
                        + registeredBook.getTitle()
        );
        System.out.println(
                "Автор книги:         "
                        + registeredBook.getAuthorName()
        );
        System.out.println(
                "Год издания:         "
                        + registeredBook.getPublicationYear()
        );

        // TODO [STAGE 8]:
        // Позже сообщение об успешной регистрации можно расширить:
        // - указанием общего количества книг в каталоге;
        // - предложением перейти к просмотру каталога;
        // - автоматическим показом карточки книги в более полном формате.
    }

    public void printApplicationFinishedMessage() {
        System.out.println();
        System.out.println("Работа приложения завершена по команде пользователя.");
    }

    public void printUnknownCommandMessage(String userCommand) {
        System.out.println();
        System.out.println(
                "Введена неизвестная команда: \""
                        + userCommand
                        + "\"."
        );
        System.out.println(
                "Повторите ввод и используйте одну из доступных команд главного меню: "
                        + "|0| |1| |2|"
        );

        // TODO [STAGE 9]:
        // Позже обработку ошибок ввода нужно сделать более гибкой:
        // - добавить универсальный валидатор команд;
        // - поддержать вложенные меню;
        // - показывать список допустимых команд для текущего раздела;
        // - логировать некорректный ввод при необходимости.
    }
}
