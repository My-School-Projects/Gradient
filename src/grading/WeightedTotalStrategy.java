package grading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * [X] 1. `public` methods must not have any side effects. That is, they must not change the parameters that
 * they are passed in any way (e.g., the `List` that is passed to the `calculate()` method must not be changed
 * in any way) and they must not change attributes that are not "owned" (i.e., attributes that are aliases) in
 * any way (e.g., the `Map` that is passed to the constructor must not be changed in any way).
 *
 * [X] 2. The `calculate()` method must calculate the weighted total of the `List` of `Grade` objects it is
 * passed.
 *      [X] 2.1. You may assume that the `calculate()` method is passed a `List` that does not contain any
 *      `null` elements.
 *      [X] 2.2. If the `List` is `null` then it must throw a `SizeException`.
 *      [X] 2.3. If the `List` is empty then it must throw a `SizeException`.
 *      [X] 2.4. Otherwise, it must return a `Grade` object with the given key and value equal to the weighted
 *      total of the `Grade` objects in the `List`.
 *          [X] 2.4.1. The weight for each element must be obtained from the `Map` using the key for that
 *          element.
 *              [X] 2.4.1.1. If the `weights` `Map` is `null` then a weight of `1.0` must be used.
 *              [X] 2.4.1.2. If the weight for a particular `Grade` is unspecified (i.e., `null`) then a
 *              weight of `1.0` must be used. Note: The `Missing` class has a method that can be used to
 *              accomplish this.
 *              [X] 2.4.1.3. If the weight for a particular `Grade` is less than `0.0` then a weight of `0.0`
 *              must be used.
 *          [X] 2.4.2. If the value of a particular `Grade` is missing (i.e., `null`) then a value of `0.0`
 *          must be used. Note: The `Missing` class has a method that can be used to accomplish this.
 *
 *      [X] 3. The default constructor must (directly or indirectly) initialize the `weights` `Map` to `null`.
 */
public class WeightedTotalStrategy implements GradingStrategy
{
    private Map<String, Double> weights;

    public WeightedTotalStrategy()
    {
        weights = new HashMap<>();
    }

    public WeightedTotalStrategy(Map<String, Double> weights)
    {
        this.weights = weights;
    }

    @Override
    public Grade calculate(String key, List<Grade> grades) throws SizeException
    {
        if (grades == null || grades.isEmpty()) throw new SizeException();

        if (weights == null)
        {
            // Each weight will not be found, and will default to `1.0`
            weights = new HashMap<>();
        }

        double weightedTotal = 0d;
        for (Grade g : grades)
        {
            // Get the weight for the key - if there is no entry for that weight, return `1.0`
            // NOTE: This behavior is UNSPECIFIED in the doc.
            Double weight = weights.getOrDefault(g.getKey(), 1d);
            Double value = g.getValue();

            // In the case of a missing weight, treat it as `1.0` as per `R2.4.1.2`
            weight = Missing.doubleValue(weight, 1d);
            // In the case that `weight < 0`, set it to `0.0` as per `R2.4.1.3`
            weight = (weight < 0) ? 0d : weight;

            // In the case that value is missing, set it to `0.0` as per `R2.4.2`
            value = (value == null) ? 0d : value;

            weightedTotal += value * weight;
        }
        return new Grade(key, weightedTotal);
    }
}
