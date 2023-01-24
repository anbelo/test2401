package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Transformer {

    @SuppressWarnings("unchecked")
    public static void mapTreeToTree(Map<String, Object> map, Tree tree) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(tree);
        Tree t = tree;
        for (String key : map.keySet()) {
            if ("head".equals(key)) {
                t = new Tree((String) map.get(key));
                tree.children.add(t);
            } else if (map.get(key) instanceof String value) {
                t.children.add(new Tree(value));
            } else if (map.get(key) instanceof Map<?, ?> child) {
                mapTreeToTree((Map<String, Object>) child, t);
            }
        }
    }

    private static int getChildrenRowCount(Tree tree) {
        int result = 0;
        if (0 < tree.children.size()) {
            result = 1;
        }
        for (Tree child : tree.children) {
            result = Math.max(result, getChildrenRowCount(child));
        }
        return result + 1;
    }

    private static int getWidth(Tree tree) {
        if (tree.children.size() == 0) {
            return 1;
        }
        int result = 0;
        for (Tree child : tree.children) {
            result += getWidth(child);
        }
        return result;
    }

    private static void getCells(Tree t, int rowCount, List<Row> rows, int rowNum) {
        int rowSpanCount = rowCount / getChildrenRowCount(t);
        rows.get(rowNum).add(new Cell(t.value, rowSpanCount, getWidth(t)));
        if (t.children.size() > 0) {
            if (rows.size() - 1 <= rowNum) {
                rows.add(new Row());
            }
        } else {
            for (int i = 1; i < rowSpanCount; i++) {
                if (rowNum + i < rows.size()) {
                    rows.get(rowNum + i).add(null);
                } else {
                    rows.add(new Row().add(null));
                }
            }
        }
        for (Tree child : t.children) {
            getCells(child, rowCount - rowSpanCount, rows, rowNum + 1);
        }
    }

    public static List<Row> transform(Map<String, Object> map) {
        Tree tree = new Tree("ROOT");
        mapTreeToTree(map, tree);
        List<Row> rows = new ArrayList<>();
        rows.add(new Row());
        getCells(tree, getChildrenRowCount(tree), rows, 0);
        rows.remove(0);
        return rows;
    }

}
