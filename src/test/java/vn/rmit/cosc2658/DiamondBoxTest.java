package vn.rmit.cosc2658;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiamondBoxTest {
    private final DiamondBox test1 = new DiamondBox("[*]");
    private final DiamondBox test2 = new DiamondBox("[[*]]");
    private final DiamondBox test3 = new DiamondBox("[**[**]*]");
    private final DiamondBox test4 = new DiamondBox("[[[*]**]*]");
    private final DiamondBox test5 = new DiamondBox("[]*");
    private final DiamondBox test6 = new DiamondBox("[[*]");
    private final DiamondBox test7 = new DiamondBox("[**][*]");


    @Test
    void isValid() {
        assertTrue(test1.isValid());
        assertTrue(test2.isValid());
        assertTrue(test3.isValid());
        assertTrue(test4.isValid());
        assertFalse(test5.isValid());
        assertFalse(test6.isValid());
        assertFalse(test7.isValid());
    }

    @Test
    void deepestLevel() {
        assertEquals(1, test1.deepestLevel());
        assertEquals(2, test2.deepestLevel());
        assertEquals(2, test3.deepestLevel());
        assertEquals(3, test4.deepestLevel());
        assertEquals("Configuration is not valid!", assertThrows(RuntimeException.class, test5::deepestLevel).getMessage());
        assertEquals("Configuration is not valid!", assertThrows(RuntimeException.class, test6::deepestLevel).getMessage());
        assertEquals("Configuration is not valid!", assertThrows(RuntimeException.class, test7::deepestLevel).getMessage());
    }

    @Test
    void maxDiamond() {
        assertEquals(1, test1.maxDiamond());
        assertEquals(1, test2.maxDiamond());
        assertEquals(3, test3.maxDiamond());
        assertEquals(2, test4.maxDiamond());
        assertEquals("Configuration is not valid!", assertThrows(RuntimeException.class, test5::maxDiamond).getMessage());
        assertEquals("Configuration is not valid!", assertThrows(RuntimeException.class, test6::maxDiamond).getMessage());
        assertEquals("Configuration is not valid!", assertThrows(RuntimeException.class, test7::maxDiamond).getMessage());
    }
}