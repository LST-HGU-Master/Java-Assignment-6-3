import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog63Test {

    @Test
    public void testTokenizedResults()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        in.inputln("The tests are currently failing because of an output mismatch."); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog63.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals( "mismatch.", prints[prints.length - 1]);
        assertEquals( "output", prints[prints.length - 2]);
        assertEquals( "an", prints[prints.length - 3]);
        assertEquals( "of", prints[prints.length - 4]);
        assertEquals( "because", prints[prints.length - 5]);
        assertEquals( "failing", prints[prints.length - 6]);
        assertEquals( "currently", prints[prints.length - 7]);
        assertEquals( "are", prints[prints.length - 8]);
        assertEquals( "tests", prints[prints.length - 9]);

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testMultipleSpaces()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        in.inputln("The   tests   are   currently   failing   because   of   an   output   mismatch."); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog63.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals( "mismatch.", prints[prints.length - 1]);
        assertEquals( "output", prints[prints.length - 2]);
        assertEquals( "an", prints[prints.length - 3]);
        assertEquals( "of", prints[prints.length - 4]);
        assertEquals( "because", prints[prints.length - 5]);
        assertEquals( "failing", prints[prints.length - 6]);
        assertEquals( "currently", prints[prints.length - 7]);
        assertEquals( "are", prints[prints.length - 8]);
        assertEquals( "tests", prints[prints.length - 9]);

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testTab()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        in.inputln("The\ttests\tare\tcurrently\tfailing\tbecause\tof\tan\toutput\tmismatch."); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog63.main(null);

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals( "mismatch.", prints[prints.length - 1]);
        assertEquals( "output", prints[prints.length - 2]);
        assertEquals( "an", prints[prints.length - 3]);
        assertEquals( "of", prints[prints.length - 4]);
        assertEquals( "because", prints[prints.length - 5]);
        assertEquals( "failing", prints[prints.length - 6]);
        assertEquals( "currently", prints[prints.length - 7]);
        assertEquals( "are", prints[prints.length - 8]);
        assertEquals( "tests", prints[prints.length - 9]);

        // undo the binding in System
        System.setOut(originalOut);
    }
}
