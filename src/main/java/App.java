import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class App {
    static File resultFile1 = new File("D:/Test_json/Test_1.txt");
    static File resultFile2 = new File("D:/Test_json/Test_2.txt");
    public static List<Map<String, Object>> ListMap = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        generate(resultFile1, resultFile2);
    }
    public static String generate(File file1, File file2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map1 = mapper.readValue(file1, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map2 = mapper.readValue(file2, new TypeReference<Map<String, Object>>() {
        });


        TreeSet<String> treeSet = new TreeSet<String>(map1.keySet());
        treeSet.addAll(map2.keySet());

        for (String key : treeSet) {


            if (map1.containsKey(key) & map2.containsKey(key)) {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                Map<String, Object> map = new HashMap<>();
                if (value1.equals(value2)) {

                    map.put("    " + key, value1);
                } else {
                    map.put("  " + "-" + " " + key, value1);
                }
                ListMap.add(map);
            }

            if (map1.containsKey(key) & (!map2.containsKey(key))) {
                Map<String, Object> map = new HashMap<>();
                Object value1 = map1.get(key);
                map.put("  " + "-" + " " + key, value1);
                ListMap.add(map);
            }

            if (map2.containsKey(key) & (!map1.containsKey(key))) {
                Map<String, Object> map = new HashMap<>();
                Object value2 = map2.get(key);
                map.put("  " + "+" + " " + key, value2);
                ListMap.add(map);
            }

            if (map1.containsKey(key) & (map2.containsKey(key))) {
                Map<String, Object> map = new HashMap<>();
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);
                if (!value1.equals(value2)) {
                    map.put("  " + "+" + " " + key, map2.get(key));
                    ListMap.add(map);
                }

            }
        }
        return toString(ListMap);
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







































