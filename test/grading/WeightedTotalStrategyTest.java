package grading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WeightedTotalStrategyTest
{
    private GradingStrategy s, n;
    private List<Grade> l;
    private Map<String, Double> w;
    private Double weightedTotal, unweightedTotal;

    @BeforeEach
    void setup()
    {
        l = new LinkedList<>();
        l.add(new Grade("A", 35));
        l.add(new Grade("B", 85));
        l.add(new Grade("C", 100));
        l.add(new Grade("D", 90));
        w = new HashMap<>();
        w.put("A", .05);
        w.put("B", .05);
        w.put("C", .4);
        w.put("D", .5);
        unweightedTotal = 310d; // 35 + 85 + 100 + 90
        weightedTotal = 91d; // 35 * .05 + 85 * .05 + 100 * .4 + 90 * .5
        s = new WeightedTotalStrategy(w);
    }

    @Test
    void specR2_2_NullListThrows()
    {
        assertThrows(SizeException.class, () ->
        {
            s.calculate("Grade", null);
        });
    }

    @Test
    void specR2_3_EmptyListThrows()
    {
        assertThrows(SizeException.class, () ->
        {
            s.calculate("Grade", new LinkedList<>());
        });
    }

    @Test
    void specR2_4_1_1_NullMapIsUnweighted() throws SizeException
    {
        n = new WeightedTotalStrategy(null);
        Grade g = n.calculate("Some Grade Name", l);
        assertEquals(unweightedTotal, g.getValue());
        assertEquals("Some Grade Name", g.getKey());
    }

    @Test
    void specR2_4_CalculateWeightedTotal() throws SizeException
    {
        Grade g = s.calculate("Arbitrary Name", l);
        assertEquals(weightedTotal, g.getValue());
        assertEquals("Arbitrary Name", g.getKey());
    }

    @Test
    void specR2_4_1_2_MissingWeightIsUnweighted() throws SizeException
    {
        l.add(new Grade("Null", 100));
        w.put("Null", null);
        s = new WeightedTotalStrategy(w);
        assertEquals(Double.valueOf(weightedTotal + 100), s.calculate("Grade", l).getValue());
    }

    @Test
    void specR2_4_1_3_NegativeWeightIsZero() throws SizeException
    {
        l.add(new Grade("Neg", 100));
        w.put("Neg", -5.2d);
        s = new WeightedTotalStrategy(w);
        assertEquals(weightedTotal, s.calculate("Grade", l).getValue());
    }

    @Test
    void specR2_4_2_MissingGradeIsZero() throws SizeException
    {
        l.add(new Grade("Missing", null));
        w.put("Missing", 100d);
        s = new WeightedTotalStrategy(w);
        assertEquals(weightedTotal, s.calculate("Grade", l).getValue());
    }

    @Test
    void specR3_DefaultConstructorIsUnweighted() throws SizeException
    {
        // NOTE: Spec R3 actually says that the `weights` map is set to `null`, but since we can only test
        // behaviors, we will just test that it BEHAVES as if `weights` is set to null.
        n = new WeightedTotalStrategy();
        Grade g = n.calculate("Some Grade Name", l);
        assertEquals(unweightedTotal, g.getValue());
        assertEquals("Some Grade Name", g.getKey());
    }
}
