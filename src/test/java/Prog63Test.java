import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @version (20220522)
 *   suporting both println and print("\n") on Windows
 **/
public class Prog63Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }

    @Test
    public void testTokenizedResults()
    {
        // action
        in.inputln("The tests are currently failing because of an output mismatch."); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog63.main(null);

        // assertion
        String prints[] = bos.toString().split("\r\n|\n");
        try {
            assertEquals( "mismatch.", prints[prints.length - 1]);
            assertEquals( "output", prints[prints.length - 2]);
            assertEquals( "an", prints[prints.length - 3]);
            assertEquals( "of", prints[prints.length - 4]);
            assertEquals( "because", prints[prints.length - 5]);
            assertEquals( "failing", prints[prints.length - 6]);
            assertEquals( "currently", prints[prints.length - 7]);
            assertEquals( "are", prints[prints.length - 8]);
            assertEquals( "tests", prints[prints.length - 9]);
        } catch (AssertionError err) {
            after();
            AssertionError asErr = new AssertionError("適切なAPIを選んでいない または 改行の仕方が不正です!");
            throw asErr;     
        }
    }

    @Test
    public void testMultipleSpaces()
    {
        // action
        in.inputln("The   tests   are   currently   failing   because   of   an   output   mismatch."); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog63.main(null);

        // assertion
        String prints[] = bos.toString().split("\r\n|\n");
        try {
            assertEquals( "mismatch.", prints[prints.length - 1]);
            assertEquals( "output", prints[prints.length - 2]);
            assertEquals( "an", prints[prints.length - 3]);
            assertEquals( "of", prints[prints.length - 4]);
            assertEquals( "because", prints[prints.length - 5]);
            assertEquals( "failing", prints[prints.length - 6]);
            assertEquals( "currently", prints[prints.length - 7]);
            assertEquals( "are", prints[prints.length - 8]);
            assertEquals( "tests", prints[prints.length - 9]);
        } catch (AssertionError err) {
            after();
            AssertionError asErr = new AssertionError("適切なAPIを選んでいない または 改行の仕方が不正です!");
            throw asErr;     
        }
    }

    @Test
    public void testTab()
    {
        // action
        in.inputln("The\ttests\tare\tcurrently\tfailing\tbecause\tof\tan\toutput\tmismatch."); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog63.main(null);

        // assertion
        String prints[] = bos.toString().split("\r\n|\n");
        try {
            assertEquals( "mismatch.", prints[prints.length - 1]);
            assertEquals( "output", prints[prints.length - 2]);
            assertEquals( "an", prints[prints.length - 3]);
            assertEquals( "of", prints[prints.length - 4]);
            assertEquals( "because", prints[prints.length - 5]);
            assertEquals( "failing", prints[prints.length - 6]);
            assertEquals( "currently", prints[prints.length - 7]);
            assertEquals( "are", prints[prints.length - 8]);
            assertEquals( "tests", prints[prints.length - 9]);
        } catch (AssertionError err) {
            after();
              AssertionError asErr = new AssertionError("適切なAPIを選んでいない または 改行の仕方が不正です!");
            throw asErr;   
        }
    }
    
    @Test
    public void testPromptMessage()
    {
        // action
        in.inputln("The tests  are currently failing."); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog63.main(null);

        // assertion
        String msg = "英文を入力してください";
        try {
            String[] prints = bos.toString().split("\r\n|\n");
            assertTrue( prints[0].contains(msg), "プログラムからのメッセージが「" + msg +"」ではありません!");
        } catch (AssertionError err) {
            after();
            throw err;   
        }
    }
}
