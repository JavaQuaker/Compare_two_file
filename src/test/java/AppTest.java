import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* "host": "hexlet.io",
  "timeout": 50,
  "proxy": "123.234.53.22",
  "follow": false,
  "test": 50
*/

/*      "timeout": 20,
        "verbose": true,
        "host": "hexlet.io"
*/
public class AppTest {
    File file1;
    File file2;
    File file3;
    File file4;
    String result;
    String resultEmpty;
    @BeforeEach
    public void beforeEach() throws IOException {
        file1 = new File("src/test/resources/Test_1.txt");
        file2 = new File("src/test/resources/Test_2.txt");

        file3 = new File("src/test/resources/Test_yaml_1.txt");
        file4 = new File("src/test/resources/Test_yaml_2.txt");

    }
    @Test
    public void Test1() throws IOException {
        result = Files.readString(Paths.get("src/test/resources/Test.txt"));
        String expected = "{" + "\n" + App.generate(file1, file2) + "}";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Test2() throws IOException {
        result = Files.readString(Paths.get("src/test/resources/Test_yaml.txt"));
        String expected = "{" + "\n" + Parser.compareYamlFile(file3, file4) + "}";
        assertThat(result).isEqualTo(expected);
    }

}

