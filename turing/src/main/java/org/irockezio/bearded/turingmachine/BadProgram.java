package org.irockezio.bearded.turingmachine;

public class BadProgram extends Exception {

    public BadProgram(String msg) {
        super(msg);
    }

    public BadProgram() {
        super();
    }
}
