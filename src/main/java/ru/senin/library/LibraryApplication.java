package ru.senin.library;

import java.util.Scanner;

public class LibraryApplication {

    public static void main(String[] args) {

        // TODO [STAGE 1]:
        // Это стартовый каркас консольного приложения.
        // На этом этапе логика временно находится в одном классе.
        // Позже нужно будет вынести:
        // 1. чтение пользовательского ввода в отдельный класс;
        // 2. печать меню и сообщений в отдельный класс;
        // 3. цикл обработки команд в отдельный runner / handler;
        // 4. бизнес-логику библиотеки в отдельные сервисы.

        Scanner consoleScanner = new Scanner(System.in);
        boolean isApplicationRunning = true;

        printApplicationHeader();

        while (isApplicationRunning) {
            printMainMenu();
            String userCommand = consoleScanner.nextLine().trim();

            switch (userCommand) {
                case "1":
                    showTestActionResult();
                    break;
                case "0":
                    System.out.println();
                    System.out.println("Работа приложения завершена по команде пользователя!");
                    isApplicationRunning = false;
                    break;
                default:
                    showUnknownCommandMessage(userCommand);
                    break;
            }
        }

        // TODO [STAGE 2]:
        // Позже нужно определить корректное завершение приложения:
        // - где и как завершается консольный цикл;
        // - где выполняется сохранение данных;
        // - где закрываются ресурсы ввода;
        // - какое финальное сообщение показывается пользователю.
    }

    private static void printApplicationHeader() {
        System.out.println();
        System.out.println("================================================================================");
        System.out.println("                     УЧЕБНАЯ СИСТЕМА УПРАВЛЕНИЯ БИБЛИОТЕКОЙ                     ");
        System.out.println("================================================================================");
        System.out.println("Текущая версия: начальный консольный каркас приложения.");
        System.out.println("Назначение этапа: проверить запуск, цикл меню и обработку команд.");
        System.out.println();

        // TODO [STAGE 3]:
        // Позже стартовый экран можно расширить:
        // - текущей датой и временем;
        // - информацией о версии приложения;
        // - сообщением о загрузке данных из файлового хранилища;
        // - количеством книг, экземпляров и читателей в системе.
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("--------------------------------- ГЛАВНОЕ МЕНЮ ---------------------------------");
        System.out.println("1 - Выполнить тестовое действие");
        System.out.println("0 - Завершить работу приложения");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Введите номер команды и нажмите Enter: ");

        // TODO [STAGE 4]:
        // Позже главное меню будет заменено на полноценную навигацию:
        // 1. Книги
        // 2. Читатели
        // 3. Выдачи и возвраты
        // 4. Отчёты
        // 5. Настройки
        // 0. Выход
    }

    private static void showTestActionResult() {
        System.out.println();
        System.out.println("Тестовое действие выполнено успешно!");
        System.out.println("Это подтверждает, что приложение корректно:");
        System.out.println("- показывает меню;");
        System.out.println("- читает ввод пользователя;");
        System.out.println("- обрабатывает команду;");
        System.out.println("- продолжает работу после выполнения команды.");

        // TODO [STAGE 5]:
        // Этот пункт меню временный.
        // Позже вместо него появятся реальные сценарии:
        // - добавление книги;
        // - регистрация читателя;
        // - выдача экземпляра книги;
        // - возврат экземпляра книги;
        // - поиск и просмотр данных.
    }

    private static void showUnknownCommandMessage(String userCommand) {
        System.out.println();
        System.out.println("Введена неизвестная команда: \"" + userCommand + "\"!");
        System.out.println("Повторите ввод и используйте одну из доступных команд главного меню: 1 или 0!");

        // TODO [STAGE 6]:
        // Позже обработку ошибок ввода нужно сделать более гибкой:
        // - добавить универсальный валидатор команд;
        // - поддержать вложенные меню;
        // - показывать список допустимых команд для текущего раздела;
        // - логировать некорректный ввод при необходимости.
    }
}
