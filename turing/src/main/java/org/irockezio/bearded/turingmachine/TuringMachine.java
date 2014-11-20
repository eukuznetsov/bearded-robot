package org.irockezio.bearded.turingmachine;

import java.util.logging.Logger;

public final class TuringMachine {

    private static final Logger logger = Logger.getLogger(TuringMachine.class.getName());

    private final int FIRST_STATE = 0;
    private final int FIRST_CELL = 0;

    public TuringMachineTape runProgram(TuringMachineProgram program, TuringMachineTape tape) throws BadProgram, BadTape {
        if (tape == null) {
            throw new BadTape();
        }

        if (program == null) {
            throw new BadProgram();
        }

        int currentState = FIRST_STATE;
        int currentCell = FIRST_CELL;

        while (true) {
            logger.fine("Tape is " + tape.toString());
            TuringMachineCellValue value = tape.getCell(currentCell);
            TuringMachineInstruction instruction = program.getInstruction(currentState, value);
            if (instruction == null) {
                throw new BadProgram("Instruction for state=" + Integer.toString(currentState) + ", value=" + value);
            }
            tape.setCellValue(currentCell, instruction.getDestinationSymbol());

            switch (instruction.getDirection()) {
                case RIGHT:
                    currentCell++;
                    break;
                case LEFT:
                    currentCell--;
                    break;
            }

            currentState = instruction.getNextState();
            if (currentState == TuringMachineInstruction.HALT_STATE) {
                break;
            }
        }
        return tape;
    }
}
