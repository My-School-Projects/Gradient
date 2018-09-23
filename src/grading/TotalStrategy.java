package grading;

import java.util.List;

/*
 * [X] 1. `public` methods must not have any side effects. That is, they must not change the parameters that
 * they are passed in any way (e.g., the `List` that is passed to the `calculate()` method must not be changed
 * in any way) and they must not change attributes that are not "owned" (i.e., attributes that are aliases) in
 * any way.
 *
 * [X] 2. The `calculate()` method must calculate the total of the `List` of `Grade` objects it is passed.
 *      [X] 2.1. You may assume that the `calculate()` method is passed a `List` that does not contain any
 *      `null` elements.
 *      [X] 2.2. If the `List` is `null` then it must throw a `SizeException`.
 *      [X] 2.3. If the `List` is empty then it must throw a `SizeException`.
 *      [X] 2.4. Otherwise, it must return a `Grade` object with the given key and a value equal to the total
 *      of the `Grade` objects in the `List`.
 *          [X] 2.4.1. If the value of a particular `Grade` is missing (i.e., `null`) then the value of `0.0`
 *          must be used. Note: The `Missing` class has a method that can be sued to accomplish this.
 */
public class TotalStrategy implements GradingStrategy
{

    @Override
    public Grade calculate(String key, List<Grade> grades) throws SizeException
    {
        if (grades == null || grades.isEmpty()) throw new SizeException();

        double total = 0d;
        for (Grade g : grades)
        {
            total += Missing.doubleValue(g.getValue());
        }
        return new Grade(key, total);
    }
}
