package ru.senin.library.console;

@FunctionalInterface
public interface ConsoleCommand {

    CommandRoutingResult execute();
}