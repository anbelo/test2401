package org.example;

import java.util.*;

class Tree {
    String value;
    List<Tree> children;

    public Tree(String value, Tree... children) {
        this.value = value;
        this.children = new ArrayList<>(Arrays.asList(children));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Objects.hashCode(value);
        result = 31 * result + Objects.hashCode(children);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tree t)) {
            return false;
        }
        return Objects.equals(t.value, this.value) && t.children.equals(this.children);
    }

    @Override
    public String toString() {
        return value + ", " + Arrays.deepToString(children.toArray());
    }
}
