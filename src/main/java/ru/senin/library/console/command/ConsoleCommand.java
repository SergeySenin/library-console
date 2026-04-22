package ru.senin.library.console.command;

@FunctionalInterface
public interface ConsoleCommand {

    CommandRoutingResult execute();
}
