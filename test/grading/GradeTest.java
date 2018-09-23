package grading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest
{
    private Grade g1, g2;

    @Test
    void specR2stringConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null);
        });
    }

    @Test
    void specR2stringDoublePrimitiveConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null, 100d);
        });
    }

    @Test
    void specR2stringDoubleConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null, Double.valueOf(100));
        });
    }

    @Test
    void specR2stringConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("");
        });
    }

    @Test
    void specR2stringDoublePrimativeConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("", 100d);
        });
    }

    @Test
    void specR2stringDoubleConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("", Double.valueOf(100d));
        });
    }

    @Test
    void specR3stringConstructorHasCorrectValue()
    {
        g1 = new Grade("Grade");
        assertEquals(Double.valueOf(0d), g1.getValue());
    }

    @Test
    void specR4_1CompareTo_ThisValueNull()
    {
        g1 = new Grade("G1", null);
        g2 = new Grade("G2"); // g2.value != null, as per R3
        assertEquals(-1, g1.compareTo(g2));
    }

    @Test
    void specR4_2CompareTo_BothNull()
    {
        g1 = new Grade("G1", null);
        g2 = new Grade("G2", null);
        assertEquals(0, g1.compareTo(g2));
    }

    @Test
    void specR4_3CompareTo_OtherValueNull()
    {
        g1 = new Grade("G1"); // g1.value != null, as per R3
        g2 = new Grade("G2", null);
        assertEquals(1, g1.compareTo(g2));
    }

    @Test
    void specR4_4CompareTo_NeitherNull()
    {
        g1 = new Grade("G1", 5);
        g2 = new Grade("G2", 4);
        int comparison = g1.getValue().compareTo(g2.getValue());
        assertEquals(comparison, g1.compareTo(g2));
        g1 = new Grade("G1", 2);
        comparison = g1.getValue().compareTo(g2.getValue());
        assertEquals(comparison, g1.compareTo(g2));
        g2 = new Grade("G2", 2);
        comparison = g1.getValue().compareTo(g2.getValue());
        assertEquals(comparison, g1.compareTo(g2));
    }
}
