package grading;

import java.util.List;
import java.util.Map;

public class WeightedTotalStrategy implements GradingStrategy
{
    public WeightedTotalStrategy(Map<String, Double> weights)
    {

    }

    @Override
    public Grade calculate(String key, List<Grade> grades) throws SizeException
    {
        return null;
    }
}
