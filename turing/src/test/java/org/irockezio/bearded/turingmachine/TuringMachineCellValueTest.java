package org.irockezio.bearded.turingmachine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TuringMachineCellValueTest {

    @Test
    public void testEquals() throws Exception {
        assertEquals(new TuringMachineCellValue(null), new TuringMachineCellValue(null));
    }
}