package ru.senin.library.console;

import java.util.Scanner;

public class ConsoleInputReader {

    private final Scanner consoleScanner;

    public ConsoleInputReader() {
        this.consoleScanner = new Scanner(System.in);
    }

    public String readCommand() {
        return consoleScanner.nextLine().trim();
    }

    // TODO [STAGE 4]:
    // Позже этот класс будет расширен методами для чтения:
    // - чисел;
    // - дат;
    // - yes/no ответов;
    // - обязательных строковых значений.
}
