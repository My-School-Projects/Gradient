package grading;

public class Grade implements Comparable<Grade>
{
    private String key;
    private Double value;

    public Grade (String key) throws IllegalArgumentException
    {
        this.key = key;
    }

    public Grade(String key, double value) throws IllegalArgumentException
    {
        this.key = key;
        this.value = value;
    }

    public Grade(String key, Double value) throws IllegalArgumentException
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public Double getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return key + ": " + value.toString();
    }

    @Override
    public int compareTo(Grade g)
    {
        return value.compareTo(g.value);
    }
}
