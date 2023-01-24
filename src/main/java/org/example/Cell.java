package org.example;

import java.util.Objects;

class Cell {
    private final String value;
    private final int rowSpan;
    private final int colSpan;

    public Cell(String value, int rowSpan, int colSpan) {
        this.value = value;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Objects.hashCode(value);
        result = 31 * result + Objects.hashCode(rowSpan);
        result = 31 * result + Objects.hashCode(colSpan);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cell c)) {
            return false;
        }
        return Objects.equals(c.value, this.value) && c.rowSpan == this.rowSpan && c.colSpan == this.colSpan;
    }

    @Override
    public String toString() {
        return "cell(" +
                "rs=" + rowSpan +
                ", cs=" + colSpan +
                ", v=" + value +
                ')';
    }
}
