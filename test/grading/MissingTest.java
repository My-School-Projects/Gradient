package grading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissingTest
{
    @Test
    void specR2_DoubleValueSingleParam()
    {
        assertEquals(5d, Missing.doubleValue(5d));
        assertEquals(0d, Missing.doubleValue(0d));
        assertEquals(0d, Missing.doubleValue(null));
    }

    @Test
    void specR3_DoubleValueTwoParams()
    {
        assertEquals(5d, Missing.doubleValue(5d, 25d));
        assertEquals(0d, Missing.doubleValue(0d, 25d));
        assertEquals(25d, Missing.doubleValue(null, 25d));
    }
}
