package grading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DropFilterTest
{
    private Grade a, b, c, d, e;
    private Filter f;
    private List<Grade> l, l0, l1, l2, l3, r;

    @BeforeEach
    void setup()
    {
        a = new Grade("A", 30.4);
        b = new Grade("B", 68.2);
        c = new Grade("C", 100);
        d = new Grade("D", 45);
        e = new Grade("E", 93.6);
        l = new LinkedList<>();
        l.add(a);
        l.add(b);
        l.add(c);
        l.add(d);
        l.add(e);
        l0 = new LinkedList<>();
        l1 = new LinkedList<>();
        l1.add(a);
        l2 = new LinkedList<>();
        l2.add(a);
        l2.add(b);
        l3 = new LinkedList<>();
        l3.add(a);
        l3.add(b);
        l3.add(c);
    }

    @Test
    void specR3_DefaultConstructorDropsHighestAndLowest() throws SizeException
    {
        f = new DropFilter();
        r = f.apply(l);
        assertFalse(r.contains(a));
        assertFalse(r.contains(c));
    }

    @Test
    void specR4_1_1_ApplyThrowsSizeExceptionOnNull()
    {
        f = new DropFilter();
        assertThrows(SizeException.class, () ->
        {
            f.apply(null);
        });
    }

    @Test
    void specR4_1_2_andR4_1_3_ApplyListTooSmall_DropHighAndLow()
    {
        // This will drop 2 elements, so the list passed to apply() must be of length 3 or higher.
        f = new DropFilter();
        assertThrows(SizeException.class, () ->
        {
            // l2 has length 2
            f.apply(l2);
        });
        assertDoesNotThrow(() ->
        {
            f.apply(l3);
        });
    }

    @Test
    void specR4_1_2_andR4_1_3_ApplyListTooSmall_DropLow()
    {
        // This will drop 1 element, so the list passed to apply() must be of length 2 or higher.
        f = new DropFilter(true, false);
        assertThrows(SizeException.class, () ->
        {
            f.apply(l1);
        });
        assertDoesNotThrow(() ->
        {
            f.apply(l2);
        });
    }

    @Test
    void specR4_1_2_andR4_1_3_ApplyListTooSmall_DropHigh()
    {
        // This will drop 1 element, so the list passed to apply() must be of length 2 or higher.
        f = new DropFilter(false, true);
        assertThrows(SizeException.class, () ->
        {
            f.apply(l1);
        });
        assertDoesNotThrow(() ->
        {
            f.apply(l2);
        });
    }

    @Test
    void specR4_1_2_andR4_1_3_ApplyListTooSmall_NoDrop()
    {
        // This will not drop any elements, so the list passed to apply() can be any length > 0.
        f = new DropFilter(false, false);
        assertThrows(SizeException.class, () ->
        {
            f.apply(l0);
        });
        assertDoesNotThrow(() ->
        {
            f.apply(l1);
        });
    }

    @Test
    void specR4_2_1_ApplyReturnsAliases() throws SizeException
    {
        // Note: This test will fail if the elements are returned out of order, even though it is not a
        // a requirement that elements are returned in order.
        f = new DropFilter(false, false); // Don't drop anything
        r = f.apply(l);
        Iterator<Grade> rit = r.iterator();
        Iterator<Grade> lit = l.iterator();
        while(rit.hasNext() && lit.hasNext())
        {
            assertSame(rit.next(), lit.next());
        }
    }

    @Test
    void specR4_2_3_1_ApplyShouldDropLowest() throws SizeException
    {
        f = new DropFilter(true, false);
        r = f.apply(l);
        // Should remove `a` from the list
        Iterator<Grade> i = r.iterator();
        assertSame(b, i.next());
        assertSame(c, i.next());
        assertSame(d, i.next());
        assertSame(e, i.next());
        assertFalse(i.hasNext());
    }

    @Test
    void specR4_2_3_2_ApplyShouldDropHighest() throws SizeException
    {
        f = new DropFilter(false, true);
        r = f.apply(l);
        // Should remove `c` from the list
        Iterator<Grade> i = r.iterator();
        assertSame(a, i.next());
        assertSame(b, i.next());
        assertSame(d, i.next());
        assertSame(e, i.next());
        assertFalse(i.hasNext());
    }

    @Test
    void specR4_2_3_3_ApplyShouldDropHighAndLow() throws SizeException
    {
        f = new DropFilter(true, true);
        r = f.apply(l);
        // Should remove `a` and `c` from the list
        Iterator<Grade> i = r.iterator();
        assertSame(b, i.next());
        assertSame(d, i.next());
        assertSame(e, i.next());
        assertFalse(i.hasNext());
    }

    @Test
    void specR4_2_3_3_ApplyShouldDropHighAndLowEvenIfSame() throws SizeException
    {
        f = new DropFilter(true, true);
        // Set `l0` to 10 copies of `a`
        for (int i = 0; i < 10; i++) l0.add(a);
        r = f.apply(l0);
        // Should remove 2 of the grades
        Iterator<Grade> rit = r.iterator();
        for (int i = 0; i < 8; i++)
        {
            assertSame(a, rit.next());
        }
        assertFalse(rit.hasNext());
    }

    @Test
    void specR4_2_3_4_ApplyShouldDropMissingGrade() throws SizeException
    {
        l.add(new Grade("NA", null));
        f = new DropFilter(true, false);
        r = f.apply(l);
        // Should remove null element
        Iterator<Grade> i = r.iterator();
        assertSame(a, i.next());
        assertSame(b, i.next());
        assertSame(c, i.next());
        assertSame(d, i.next());
        assertSame(e, i.next());
        assertFalse(i.hasNext());
    }
}
