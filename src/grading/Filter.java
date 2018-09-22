package grading;

import java.util.List;

public interface Filter
{
    public List<Grade> apply(List<Grade> list);
}
