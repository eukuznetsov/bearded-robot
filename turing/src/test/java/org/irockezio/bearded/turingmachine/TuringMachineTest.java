package org.irockezio.bearded.turingmachine;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class TuringMachineTest {

    @Test
    public void testRunProgram() throws Exception {
        try {
            TuringMachineTape tape = new TuringMachineTape(new TuringMachineCellValue('a'));
            TuringMachineProgram program = new TuringMachineProgram();
            TuringMachine.runProgram(program, tape);
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
            TuringMachineTape result = TuringMachine.runProgram(program, tape);
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
            TuringMachineTape result = TuringMachine.runProgram(new TuringMachineProgram(instructions), tape);
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
        TuringMachineTape result = TuringMachine.runProgram(new TuringMachineProgram(instructions), tape);
        TuringMachineTape standard = new TuringMachineTape(
                new TuringMachineCellValue(null)
        );
        assertEquals(result, standard);

        TuringMachineTape tape1 = new TuringMachineTape(
                new TuringMachineCellValue('1')
        );
        TuringMachineProgram program1 = new TuringMachineProgram(
                TuringMachineInstruction.parseInstructions(
                        "0 1 0 r 0",
                        "0   n halt"
                )
        );
        TuringMachineTape resultTape1 = TuringMachine.runProgram(program1, tape1);
        TuringMachineTape standardTape1 = new TuringMachineTape(
                new TuringMachineCellValue('0')
        );
        assertEquals(standardTape1, resultTape1);
    }

}