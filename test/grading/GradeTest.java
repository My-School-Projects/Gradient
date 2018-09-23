package grading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest
{
    private Grade grade;

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
        grade = new Grade("Grade");
        assertEquals(Double.valueOf(0d), grade.getValue());
    }
}
