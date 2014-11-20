package org.irockezio.bearded.turingmachine;

public class TuringMachineCellValue {

    private Character value;

    public TuringMachineCellValue(Character value) {
        this.value = value;
    }


    public Character getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof TuringMachineCellValue)) {
            return false;
        }
        TuringMachineCellValue compare = (TuringMachineCellValue) object;
        if (this.getValue() == null && compare.getValue() == null) {
            return true;
        }
        if ((this.getValue() == null) || (compare.getValue() == null)) {
            return false;
        }
        return this.getValue().equals(compare.getValue());
    }

}
