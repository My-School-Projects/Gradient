package grading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TotalStrategyTest
{
    private TotalStrategy s;
    private List<Grade> l;
    private Double total;
    private Grade r;

    @BeforeEach
    void setup()
    {
        s = new TotalStrategy();
        l = new LinkedList<>();
        l.add(new Grade("A", 65));
        l.add(new Grade("B", 80));
        l.add(new Grade("C", 40));
        l.add(new Grade("D", 100));
        total = 285d;
    }

    @Test
    void specR2_2_NullListThrows()
    {
        assertThrows(SizeException.class, () ->
        {
            s.calculate("Key", null);
        });
    }

    @Test
    void specR2_3_EmptyListThrows()
    {
        assertThrows(SizeException.class, () ->
        {
            s.calculate("Key", new LinkedList<>());
        });
    }

    @Test
    void specR2_4_CalculateTotal() throws SizeException
    {
        r = s.calculate("Result", l);
        assertEquals("Result", r.getKey());
        assertEquals(total, r.getValue());
    }

    @Test
    void specR2_4_1_NullIsZero() throws SizeException
    {
        l.add(new Grade("E", null));
        r = s.calculate("Result", l);
        assertEquals(total, r.getValue());
    }
}