package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradientTest
{
    private static final PrintStream console = System.out;
    private ByteArrayOutputStream capturedByteArrayStream;
    private PrintStream captured;
    private String output;

    @BeforeEach
    void setup()
    {
        capturedByteArrayStream = new ByteArrayOutputStream();
        captured = new PrintStream(capturedByteArrayStream);
    }

    @Test
    void complete01()
    {
        String[] args = {"20", "18", "5", "15", "20", "20", "20", "0", "5", "10", "15", "80", "75"};

        captureConsole();
        Gradient.main(args);
        output = getCapturedOutput();
        assertEquals("Course Grade:  80.7\r\n", output);
    }

    @Test
    void complete02()
    {
        String[] args = {"20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "100", "100"};

        captureConsole();
        Gradient.main(args);
        output = getCapturedOutput();
        assertEquals("Course Grade: 100.0\r\n", output);
    }

    @Test
    void complete03()
    {
        String[] args = {"10", "10", "5", "15", "5", "20", "20", "0", "0", "10", "15", "60", "45"};

        captureConsole();
        Gradient.main(args);
        output = getCapturedOutput();
        assertEquals("Course Grade:  54.0\r\n", output);
    }

    @Test
    void missingAll()
    {
        String[] args = new String[13];
        for (int i = 0; i < args.length; i++)
        {
            args[i] = "NA";
        }
        captureConsole();
        Gradient.main(args);
        output = getCapturedOutput();
        assertEquals("Course Grade:   0.0\r\n", output);
    }

    @Test
    void missingOneInCategory()
    {
        String[] args = {"20", "18", "5", "15", "20", "20", "20", "NA", "5", "10", "15", "80", "75"};

        captureConsole();
        Gradient.main(args);
        output = getCapturedOutput();
        assertEquals("Course Grade:  80.7\r\n", output);
    }

    @Test
    void missingOneInEachCategory()
    {
        String[] args = {"20", "18", "5", "15", "NA", "20", "20", "5", "0", "NA", "15", "NA", "NA"};

        captureConsole();
        Gradient.main(args);
        output = getCapturedOutput();
        assertEquals("Course Grade:  35.2\r\n", output);
    }

    @Test
    void missingMultipleInEachCategory()
    {
        String[] args = {"NA", "20", "5", "15", "NA", "NA", "20", "NA", "0", "NA", "15", "NA", "NA"};

        captureConsole();
        Gradient.main(args);
        output = getCapturedOutput();
        assertEquals("Course Grade:  19.5\r\n", output);
    }

    private void captureConsole()
    {
        System.setOut(captured);
    }

    private String getCapturedOutput()
    {
        System.out.flush();
        System.setOut(console);
        String output = capturedByteArrayStream.toString();
        capturedByteArrayStream.reset();
        return output;
    }
}