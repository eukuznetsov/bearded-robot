package org.irockezio.bearded.turingmachine;

public class InvalidCommand extends Exception {
    private String command;

    public InvalidCommand(String command) {
        this.command = command;
    }

    @SuppressWarnings("unused")
    public String getCommand() {
        return command;
    }
}
