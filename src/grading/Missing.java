package grading;

/*
 * [X] 1. The default `double` value of a missing value must be `0.0`.
 *
 * [X] 2. The method `doubleValue(Double number)` must return the `double` value of the `Double` parameter
 * unless it is `null`, in which case it must return the default `double` value of a missing value.
 *
 * [X] 3. The method `doubleValue(Double number, double missingValue)` must return the `double` value of the
 * `Double` parameter unless it is `null`, in which case it must return `missingValue`.
 */
public class Missing
{
    private static final double DEFAULT_MISSING_VALUE = 0;

    public static double doubleValue(Double number)
    {
        return (number != null) ? number : DEFAULT_MISSING_VALUE;
    }

    public static double doubleValue(Double number, double missingValue)
    {
        return (number != null) ? number : missingValue;
    }
}
