import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {

    public static File resultFile3 = new File("src/test/resources/test_yaml_1.txt");
    public static File resultFile4 = new File("src/test/resources/test_yaml_2.txt");
    public static List<Map<String, Object>> listMap = new ArrayList<>();

    public static String compareYamlFile(File file3, File file4) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        Map<String, Object> map1 = mapper.readValue(resultFile3, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map2 = mapper.readValue(resultFile4, new TypeReference<Map<String, Object>>() {
        });

        Object value1;
        String key1 = null;
        Object value2;
        String key2 = null;

        List<Object> list = new ArrayList<>();

        for (Map.Entry<String, Object> result : map1.entrySet()) {
            key1 = result.getKey();
            value1 = result.getValue();
            list.add(value1);
        }
        for (Map.Entry<String, Object> result : map2.entrySet()) {
            key2 = result.getKey();
            value2 = result.getValue();
            list.add(value2);
        }
        Map<String, Object> newMap1 = (Map<String, Object>) map1.get(key1);
        Map<String, Object> newMap2 = (Map<String, Object>) map2.get(key2);

        TreeSet<String> treeSet = new TreeSet<>(newMap1.keySet());
        treeSet.addAll(newMap2.keySet());


        for (String key : treeSet) {

            if (newMap1.containsKey(key) & newMap2.containsKey(key)) {
                Object valueMap1 = newMap1.get(key);
                Object valueMap2 = newMap2.get(key);

                Map<String, Object> map = new HashMap<>();
                if (valueMap1.equals(valueMap2)) {

                    map.put("    " + key, valueMap1);
                } else {
                    map.put("  " + "-" + " " + key, valueMap1);
                }
                listMap.add(map);
            }

            if (newMap1.containsKey(key) & (!newMap2.containsKey(key))) {
                Map<String, Object> map = new HashMap<>();
                Object valueMap1 = newMap1.get(key);
                map.put("  " + "-" + " " + key, valueMap1);
                listMap.add(map);
            }

            if (newMap2.containsKey(key) & (!newMap1.containsKey(key))) {
                Map<String, Object> map = new HashMap<>();
                Object valueMap2 = newMap2.get(key);
                map.put("  " + "+" + " " + key, valueMap2);
                listMap.add(map);
            }

            if (newMap1.containsKey(key) & (newMap2.containsKey(key))) {
                Map<String, Object> map = new HashMap<>();
                Object valueMap1 = newMap1.get(key);
                Object valueMap2 = newMap2.get(key);
                if (!valueMap1.equals(valueMap2)) {
                    map.put("  " + "+" + " " + key, newMap2.get(key));
                    listMap.add(map);
                }
            }
        }
        return toString(listMap);
    }

    public static String toString(List<Map<String, Object>> list) {
        StringBuilder builder = new StringBuilder();
        System.out.println("{");
        for (Map<String, Object> result : list) {
            for (String key : result.keySet()) {
                Object value = result.get(key);
                builder.append(key).append(": " + " ").append(value).append("\n");
            }
        }
        System.out.println(builder.toString().replaceAll("\\s+$", ""));
        System.out.println("}");
        return builder.toString();
    }
}
