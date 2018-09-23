package grading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest
{
    private Grade grade;

    // R2
    @Test
    void stringConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null);
        });
    }

    // R2
    @Test
    void stringDoublePrimitiveConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null, 100d);
        });
    }

    // R2
    @Test
    void stringDoubleConstructorThrowsOnNullKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade(null, Double.valueOf(100));
        });
    }

    // R2
    @Test
    void stringConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("");
        });
    }

    // R2
    @Test
    void stringDoublePrimativeConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("", 100d);
        });
    }

    // R2
    @Test
    void stringDoubleConstructorThrowsOnEmptyKey()
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new Grade("", Double.valueOf(100d));
        });
    }
}
