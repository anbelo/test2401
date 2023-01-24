package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Row {

    private final List<Cell> cells = new ArrayList<>();

    public Row add(Cell cell) {
        cells.add(cell);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cells);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Row r)) {
            return false;
        }
        return r.cells.equals(this.cells);
    }

    @Override
    public String toString() {
        return cells.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
