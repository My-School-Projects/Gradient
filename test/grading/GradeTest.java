package grading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest
{
    private Grade g1, g2;

    @Test
    void specR2_StringConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null);
        });
    }

    @Test
    void specR2_StringDoublePrimitiveConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null, 100d);
        });
    }

    @Test
    void specR2_StringDoubleConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null, Double.valueOf(100));
        });
    }

    @Test
    void specR2_StringConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("");
        });
    }

    @Test
    void specR2_StringDoublePrimativeConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("", 100d);
        });
    }

    @Test
    void specR2_StringDoubleConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("", Double.valueOf(100d));
        });
    }

    @Test
    void specR3_StringConstructorHasCorrectValue()
    {
        g1 = new Grade("Grade");
        assertEquals(Double.valueOf(0d), g1.getValue());
    }

    @Test
    void specR4_1_CompareTo_ThisValueNull()
    {
        g1 = new Grade("G1", null);
        g2 = new Grade("G2"); // g2.value != null, as per R3
        assertEquals(-1, g1.compareTo(g2));
    }

    @Test
    void specR4_2_CompareTo_BothNull()
    {
        g1 = new Grade("G1", null);
        g2 = new Grade("G2", null);
        assertEquals(0, g1.compareTo(g2));
    }

    @Test
    void specR4_3_CompareTo_OtherValueNull()
    {
        g1 = new Grade("G1"); // g1.value != null, as per R3
        g2 = new Grade("G2", null);
        assertEquals(1, g1.compareTo(g2));
    }

    @Test
    void specR4_4_CompareTo_NeitherNull()
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

    @Test
    void specR5_1_ToString_ValueNotNull_SingleDecimal()
    {
        assertEquals("G1:  23.5", new Grade("G1", 23.5).toString());
    }

    @Test
    void specR5_1_ToString_ValueNotNull_DoubleDecimal()
    {
        assertEquals("G2:  84.3", new Grade("G2", 84.25).toString());
    }

    @Test
    void specR5_1_ToString_ValueNotNull_NoDecimal()
    {
        assertEquals("G3: 100.0", new Grade("G3", 100).toString());
    }

    @Test
    void specR5_2_ToString_ValueNull()
    {
        assertEquals("G4:    NA", new Grade("G4", null).toString());
    }
}
