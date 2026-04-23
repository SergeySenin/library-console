package ru.senin.library.console.output;

import java.util.Objects;

public class ConsoleApplicationPrinter {

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
        System.out.println("1 - Показать зарегистрированные книги");
        System.out.println("2 - Зарегистрировать книгу");
        System.out.println("3 - Найти книгу по идентификатору");
        System.out.println("4 - Найти книгу по названию");
        System.out.println("5 - Найти книгу по автору");
        System.out.println("6 - Найти книгу по году издания");
        System.out.println("7 - Редактировать книгу");
        printSeparatorLine();
        System.out.print("Введите номер команды и нажмите Enter: ");

        // TODO [STAGE 6]:
        // Позже главное меню будет заменено на полноценную навигацию:
        // - 1. Книги;
        // - 2. Читатели;
        // - 3. Выдачи и возвраты;
        // - 4. Отчёты;
        // - 5. Настройки;
        // - 0. Выход.
    }

    public void printApplicationFinishedMessage() {
        System.out.println();
        System.out.println("Работа приложения завершена по команде пользователя.");
    }

    public void printValidationError(String errorMessage) {
        Objects.requireNonNull(
                errorMessage,
                "Error message must not be null."
        );

        System.out.println();
        System.out.println(
                "Ошибка ввода: "
                        + errorMessage
        );
    }

    public void printUnknownCommandMessage(String userCommand) {
        Objects.requireNonNull(
                userCommand,
                "User command must not be null."
        );

        System.out.println();
        System.out.println(
                "Введена неизвестная команда: \""
                        + userCommand
                        + "\"."
        );
        System.out.println(
                "Повторите ввод и используйте одну из доступных команд главного меню: "
                        + "\n|0| |1| |2| |3| |4| |5| |6| |7|"
        );

        // TODO [STAGE 13]:
        // Позже обработку ошибок ввода нужно сделать более гибкой:
        // - поддержать вложенные меню;
        // - показывать список допустимых команд для текущего раздела;
        // - унифицировать сообщения об ошибках для разных экранов;
        // - логировать некорректный ввод при необходимости.
    }

    private void printSeparatorLine() {
        System.out.println("--------------------------------------------------------------------------------");
    }
}
