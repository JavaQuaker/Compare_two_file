import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class App {
    static File resultFile1 = new File("D:/Test_json/Test_1.txt");
    static File resultFile2 = new File("D:/Test_json/Test_2.txt");


    public static void main(String[] args) throws IOException {

        generate(resultFile1, resultFile2);
    }
    public static void generate(File file1, File file2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();


        Map<String, Object> map1 = mapper.readValue(file1, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map2 = mapper.readValue(file2, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map1);
        System.out.println(map2);


        Set<String> keys = new HashSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        List<String> listKye = new ArrayList<>(keys);
        Collections.sort(listKye);
        System.out.println(listKye);

        Map<String, Object> map = new HashMap<>();
        for (String key : listKye) {

            if (map1.containsKey(key) & map2.containsKey(key)) {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                if (value1.equals(value2)) {
                    map.put(key, value1);
                } else {
                    map.put("-" + key, value1);
                }
            }
            if (map1.containsKey(key) & (!map2.containsKey(key))) {
                Object value1 = map1.get(key);
                map.put("-" + key, value1);
            }
            if (map2.containsKey(key) & (!map1.containsKey(key))) {
                Object value2 = map2.get(key);
                map.put("+" + key, value2);
            }
            if (map1.containsKey(key) & (map2.containsKey(key))) {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);
                 if (!value1.equals(value2)) {
                     map.put("+" + key, map2.get(key));
                 }
            }
        }
        System.out.println(map);
    }

}
































