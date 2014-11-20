package org.irockezio.bearded.turingmachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class TuringMachineTape implements Iterable {

    public static final int MAX_SIZE = 100;
    private static final Logger logger = Logger.getLogger(TuringMachineTape.class.getName());

    private List<TuringMachineCellValue> cells = new ArrayList<>();

    public TuringMachineTape(TuringMachineCellValue... cellValues) {
        Collections.addAll(cells, cellValues);
    }

    @Override
    public Iterator<TuringMachineCellValue> iterator() {
        return cells.iterator();
    }

    public TuringMachineCellValue getCell(int coordinate) {
        if (coordinate < 0) {
            return null;
        }
        enlargeTape(coordinate + 1);
        return cells.get(coordinate);
    }

    public void setCellValue(int cell, TuringMachineCellValue value) {
        enlargeTape(cell + 1);
        cells.set(cell, value);
    }

    private void enlargeTape(int size) {
        logger.fine("Enlarge tape size from " + cells.size() + " to " + size);
        if (size > MAX_SIZE) {
            logger.severe("Too big length size: " + size);
            throw new IllegalArgumentException("Length is too big");
        }
        while (size > cells.size()) {
            cells.add(new TuringMachineCellValue(null));
        }
        logger.fine("Tape size is " + cells.size());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof TuringMachineTape)) {
            return false;
        }
        Iterator iterator = this.iterator();
        Iterator objIterator = ((TuringMachineTape) object).iterator();
        while (iterator.hasNext()) {
            if (!objIterator.next().equals(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (TuringMachineCellValue value : cells) {
            result.append(value).append(" ");
        }
        return result.toString();
    }
}
