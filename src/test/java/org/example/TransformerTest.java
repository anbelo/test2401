package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TransformerTest {

    private Map<String, Object> map;
    private List<Row> expectedRows;

    @Before
    public void setUp() {
        map = new LinkedHashMap<>() {{
            put("t1", "V1");
            put("t2", new LinkedHashMap<String, Object>() {{
                put("head", "V2");
                put("t3", new LinkedHashMap<String, Object>() {{
                    put("head", "V3");
                    put("t5", "V5");
                    put("t6", "V6");
                }});
                put("t4", new LinkedHashMap<String, Object>() {{
                    put("head", "V4");
                    put("t7", "V7");
                    put("t8", "V8");
                }});
            }});
        }};

        expectedRows = new ArrayList<>();
        expectedRows.add(new Row()
                .add(new Cell("V1", 3, 1))
                .add(new Cell("V2", 1, 4)));
        expectedRows.add(new Row()
                .add(null)
                .add(new Cell("V3", 1, 2))
                .add(new Cell("V4", 1, 2)));
        expectedRows.add(new Row()
                .add(null)
                .add(new Cell("V5", 1, 1))
                .add(new Cell("V6", 1, 1))
                .add(new Cell("V7", 1, 1))
                .add(new Cell("V8", 1, 1)));
    }

    @Test
    public void transform() {
        List<Row> rows = Transformer.transform(map);
        int[] i = new int[]{0};
        rows.forEach(row -> System.out.printf("rows[%d] = %s\n", i[0]++, row));
        Assert.assertEquals(expectedRows, rows);

    }

    @Test
    public void mapTreeToTree() {
        Tree root = new Tree("ROOT");
        Tree expected = new Tree("ROOT",
                new Tree("V1"),
                new Tree("V2",
                        new Tree("V3",
                                new Tree("V5"),
                                new Tree("V6")
                        ),
                        new Tree("V4",
                                new Tree("V7"),
                                new Tree("V8")
                        )
                )
        );
        Transformer.mapTreeToTree(map, root);
        Assert.assertEquals(expected, root);
    }

    @Test
    public void mapTreeToTreeWhenEmpty() {
        Tree root = new Tree("ROOT");
        Tree expected = new Tree("ROOT");
        Transformer.mapTreeToTree(Map.of(), root);
        assertEquals(expected, root);
    }

}