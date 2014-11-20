package org.irockezio.bearded.turingmachine;

import junit.framework.Assert;
import org.junit.Test;

public class TuringMachineTapeTest extends Assert {

    @Test
    public void testGetCell() throws Exception {
        try {
            TuringMachineTape tape = new TuringMachineTape();
            for (int i = 0; i <= TuringMachineTape.MAX_SIZE; i++) {
                tape.getCell(i);
            }
            fail();
        } catch (IllegalArgumentException e) {
        }

        try {
            TuringMachineTape tape = new TuringMachineTape();
            for (int i = 0; i < TuringMachineTape.MAX_SIZE; i++) {
                tape.getCell(i);
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEquals() throws Exception {
        TuringMachineTape tape1 = new TuringMachineTape(
                new TuringMachineCellValue('a'),
                new TuringMachineCellValue(null)
        );
        TuringMachineTape tape2 = new TuringMachineTape(
                new TuringMachineCellValue('a'),
                new TuringMachineCellValue(null)
        );
        assertEquals(tape1, tape2);

        //Test null as value
        tape1 = new TuringMachineTape(
                new TuringMachineCellValue('a')
        );
        tape2 = new TuringMachineTape(
                new TuringMachineCellValue(null)
        );
        assertFalse(tape1.equals(tape2));

    }
}