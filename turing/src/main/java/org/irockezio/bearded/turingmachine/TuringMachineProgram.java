package org.irockezio.bearded.turingmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TuringMachineProgram {

    private List<TuringMachineInstruction> instructions =
            new ArrayList<>();

    public TuringMachineProgram(TuringMachineInstruction... instructions) {
        this(Arrays.asList(instructions));
    }

    public TuringMachineProgram(List<TuringMachineInstruction> instructions) {
        this.instructions.addAll(instructions);
    }

    public void addInstruction(TuringMachineInstruction instruction) {
        instructions.add(instruction);
    }

    public TuringMachineInstruction getInstruction(int state, TuringMachineCellValue cellValue) {
        for (TuringMachineInstruction instruction : instructions) {
            if (instruction.getSourceSymbol().equals(cellValue) &&
                    instruction.getState() == state
                    ) return instruction;
        }
        return null;
    }
}
