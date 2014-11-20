package org.irockezio.bearded.turingmachine;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class TuringMachineTest {

    @Test
    public void testRunProgram() throws Exception {
        TuringMachine machine = new TuringMachine();

        try {
            TuringMachineTape tape = new TuringMachineTape(new TuringMachineCellValue('a'));
            TuringMachineProgram program = new TuringMachineProgram();
            machine.runProgram(program, tape);
            fail();
        } catch (BadProgram e) {
        }

        try {
            TuringMachineTape tape = new TuringMachineTape(new TuringMachineCellValue('a'));
            TuringMachineProgram program = new TuringMachineProgram();
            TuringMachineInstruction instruction = new TuringMachineInstruction(
                    0, new TuringMachineCellValue('a'), new TuringMachineCellValue('b'),
                    TapeDirection.NONE, -1
            );
            program.addInstruction(instruction);
            TuringMachineTape result = machine.runProgram(program, tape);
            assertEquals(result.getCell(0), new TuringMachineCellValue('b'));
        } catch (Exception e) {
            fail();
        }

        try {
            List<TuringMachineInstruction> instructions = TuringMachineInstruction.parseInstructions(
                    "0 a a n halt"
            );
            TuringMachineTape tape = new TuringMachineTape(
                    new TuringMachineCellValue('a'),
                    new TuringMachineCellValue('b'),
                    new TuringMachineCellValue('c')
            );
            TuringMachineTape result = machine.runProgram(new TuringMachineProgram(instructions), tape);
            TuringMachineTape standard = new TuringMachineTape(
                    new TuringMachineCellValue('a'),
                    new TuringMachineCellValue('b'),
                    new TuringMachineCellValue('c')
            );
            assertEquals(result, standard);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        List<TuringMachineInstruction> instructions = TuringMachineInstruction.parseInstructions(
                "0 a  n halt"
        );
        TuringMachineTape tape = new TuringMachineTape(
                new TuringMachineCellValue('a')
        );
        TuringMachineTape result = machine.runProgram(new TuringMachineProgram(instructions), tape);
        TuringMachineTape standard = new TuringMachineTape(
                new TuringMachineCellValue(null)
        );
        assertEquals(result, standard);

    }

}