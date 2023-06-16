import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class App {
    static File resultFile1 = new File("D:/Test_json/Test_1.txt");
    static File resultFile2 = new File("D:/Test_json/Test_2.txt");
    static List<Map<String, Object>> ListMap = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        generate(resultFile1, resultFile2);
    }
    public static void generate(File file1, File file2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map1 = mapper.readValue(file1, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map2 = mapper.readValue(file2, new TypeReference<Map<String, Object>>() {
        });


        TreeSet<String> treeSet = new TreeSet<String>(map1.keySet());
        treeSet.addAll(map2.keySet());

        for (String key : treeSet) {

            Map<String, Object> map = new HashMap<>();
            if (map1.containsKey(key) & map2.containsKey(key)) {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                if (value1.equals(value2)) {
                    map.put("    " + key, value1);
                } else {
                    map.put("  " + "-" + " " + key, value1);
                }
            }
            Map<String, Object> map3 = new HashMap<>();
            if (map1.containsKey(key) & (!map2.containsKey(key))) {
                Object value1 = map1.get(key);
                map3.put("  " + "-" + " " + key, value1);
            }
            Map<String, Object> map4 = new HashMap<>();
            if (map2.containsKey(key) & (!map1.containsKey(key))) {
                Object value2 = map2.get(key);
                map4.put("  " + "+" + " " + key, value2);
            }
            Map<String, Object> map5 = new HashMap<>();
            if (map1.containsKey(key) & (map2.containsKey(key))) {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);
                if (!value1.equals(value2)) {
                    map5.put("  " + "+" + " " + key, map2.get(key));

                }
            }
            ListMap.add(map);
            ListMap.add(map3);
            ListMap.add(map4);
            ListMap.add(map5);
        }
        toString(ListMap);
    }
    public static String toString(List<Map<String, Object>> List) {
            StringBuilder builder = new StringBuilder();
            System.out.println("{");
            for (Map<String, Object> result : ListMap) {
                for (String key : result.keySet()) {
                    Object value = result.get(key);
                    builder.append(key).append(": " + " ").append(value).append("\n");
                }
            }

            System.out.println(builder.toString().replaceAll("\\s+$",""));
            System.out.println("}");
            return builder.toString();
        }
    }







































