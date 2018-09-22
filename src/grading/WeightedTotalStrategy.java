package grading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return null;
    }
}
