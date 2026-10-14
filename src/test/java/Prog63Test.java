import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import java.nio.file.Files;
/**
 * @version (20220522) supporting both println and print("\n") on Windows
 * @version (20261014) revised for full token checking, API usage verification, and try-catch removal
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

    
    private String[] getOutputTokens() {
        String rawOutput = bos.toString().replace("\r\n", "\n");
        String cleanOutput = rawOutput
            .replace("英文を入力してください：", "")
            .replace("英文を入力してください:", "")
            .replace("英文を入力してください", "")
            .trim();

        if (cleanOutput.isEmpty()) {
            return new String[0];
        }
        return cleanOutput.split("\n");
    }

    @Test
    public void testPromptMessage() {
        in.inputln("To be, or not to be, this is the question.");
        Prog63.main(null);

        String output = bos.toString().replace("\r\n", "\n");
        assertTrue(output.contains("英文を入力してください"), 
            "「英文を入力してください」というプロンプトメッセージが出力されていません!"
        );
    }

    @Test
    public void testStandardExecutionExample() {
        in.inputln("To be, or not to be, this is the question.");
        Prog63.main(null);

        String[] expected = {
            "To", "be,", "or", "not", "to", "be,", "this", "is", "the", "question."
        };
        String[] actual = getOutputTokens();

        assertArrayEquals(expected, actual, 
            "実行例の英文に対する分割出力結果が期待値と一致しません!（最初の単語から最後の単語まで改行区切りで出力されていますか？）"
        );
    }

    @Test
    public void testMultipleSpaces()
    {
        in.inputln("The   tests   are   currently   failing.");
        Prog63.main(null);

        String[] expected = {
            "The", "tests", "are", "currently", "failing."
        };
        String[] actual = getOutputTokens();

        assertArrayEquals(expected, actual, 
            "連続する半角スペースが含まれる場合の分割処理が正しくありません! 空のトークンが出力されていないか確認してください。"
        );
    }

    @Test
    public void testTab()
    {
        // action
        in.inputln("The\ttests\tare\tcurrently\tfailing.");; // 標準入力をテストする場合
        Prog63.main(null);

        String[] expected = {
            "The", "tests", "are", "currently", "failing."
        };
        String[] actual = getOutputTokens();

        assertArrayEquals(expected, actual, 
            "タブ文字（\\t）区切りの分割処理が正しくありません! 半角スペースとタブの両方を区切り文字として指定してください。"
        );
    }
    
    @Test
    public void testApiUsage() {
        File file = new File("Prog63.java");
        if (!file.exists()) file = new File("src/Prog63.java");
        if (!file.exists()) file = new File("src/main/java/Prog63.java");

        assertTrue(file.exists(), "ソースファイル Prog63.java が見つかりません。");

        try {
            String code = Files.readString(file.toPath());
            String cleanCode = code
                .replaceAll("//.*", "")
                .replaceAll("(?s)/\\*.*?\\*/", "");

            boolean usesUtil = cleanCode.contains("java.util") 
                || cleanCode.contains("StringTokenizer") 
                || cleanCode.contains("Scanner") 
                || cleanCode.contains("Pattern")
                || cleanCode.contains("Arrays");

            assertTrue(usesUtil, 
                "課題の指示通り、java.util パッケージのクラス（StringTokenizer, Scanner など）を使用して文字列の分割を行ってください。"
            );
        } catch (IOException e) {
            fail("ソースファイルの読み込みに失敗しました: " + e.getMessage());
        }
    }
}
