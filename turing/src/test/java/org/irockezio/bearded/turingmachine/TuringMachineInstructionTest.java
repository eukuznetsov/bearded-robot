package org.irockezio.bearded.turingmachine;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class TuringMachineInstructionTest {

    @Test
    public void testParseInstructions() throws Exception {
        try {
            List<TuringMachineInstruction> instructions = TuringMachineInstruction.parseInstructions("0 a b n 0");
            TuringMachineInstruction instruction = new TuringMachineInstruction(0,
                    new TuringMachineCellValue('a'), new TuringMachineCellValue('b'), TapeDirection.NONE, 0);
            assertEquals(instructions.get(0), instruction);
        } catch (InvalidCommand invalidCommand) {
            fail();
        }

        try {
            List<TuringMachineInstruction> instructions = TuringMachineInstruction.parseInstructions("0 a b n halt");
            TuringMachineInstruction instruction = new TuringMachineInstruction(0,
                    new TuringMachineCellValue('a'), new TuringMachineCellValue('b'), TapeDirection.NONE, TuringMachineInstruction.HALT_STATE);
            assertEquals(instructions.get(0), instruction);
        } catch (InvalidCommand invalidCommand) {
            fail();
        }

        try {
            List<TuringMachineInstruction> instructions = TuringMachineInstruction.parseInstructions("0  b n halt");
            TuringMachineInstruction instruction = new TuringMachineInstruction(0,
                    new TuringMachineCellValue(null), new TuringMachineCellValue('b'), TapeDirection.NONE, TuringMachineInstruction.HALT_STATE);
            assertEquals(instructions.get(0), instruction);
        } catch (InvalidCommand invalidCommand) {
            fail(invalidCommand.getMessage());
        }

    }
}