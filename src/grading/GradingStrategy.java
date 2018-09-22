package grading;

import java.util.List;

public interface GradingStrategy
{
    public Grade calculate(String key, List<Grade> grades) throws SizeException;
}
