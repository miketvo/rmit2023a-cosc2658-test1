package vn.rmit.cosc2658;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JunctionMapTest {
    private final int[][] test1Config = {
            {0, 1, 1, 0},
            {0, 0, 0, 1},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
    };
    private final JunctionMap test1 = new JunctionMap(test1Config);

    private final int[][] test2Config = {
            {0, 1, 1, 0},
            {0, 0, 0, 1},
            {0, 1, 0, 0},
            {0, 0, 0, 0},  // 3 is a dead-end
    };
    private final JunctionMap test2 = new JunctionMap(test2Config);

    private final int[][] test3Config = {  // undirected graph
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0},
    };
    private final JunctionMap test3 = new JunctionMap(test3Config);


    @Test
    void hasOneWayStreet() {
        assertTrue(test1.hasOneWayStreet());
        assertTrue(test2.hasOneWayStreet());
        assertFalse(test3.hasOneWayStreet());
    }

    @Test
    void hasDeadEnd() {
        assertFalse(test1.hasDeadEnd());
        assertTrue(test2.hasDeadEnd());
        assertFalse(test3.hasDeadEnd());
    }

    @Test
    void getShortestPath() {
        assertEquals("Source cannot be the same as Destination!", assertThrows(
                IllegalArgumentException.class,
                () -> test3.getShortestPath(0, 0)
        ).getMessage());

        assertEquals("0 -> 1", test1.getShortestPath(0, 1));
        assertEquals("0 -> 1 -> 3", test1.getShortestPath(0, 3));
        assertEquals("", test1.getShortestPath(1, 0));
    }
}