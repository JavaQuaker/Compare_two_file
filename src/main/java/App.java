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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj.hashCode() != this.hashCode()) {
            return false;
        }
        return true;
    }

    public int HashCode() {
        return hashCode();
    }

    public static void generate(File file1, File file2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();


        Map<String, Object> map1 = mapper.readValue(file1, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map2 = mapper.readValue(file2, new TypeReference<Map<String, Object>>() {
        });

        System.out.println(map1);
        System.out.println(map2);

        StringBuilder builder = new StringBuilder();

        Set<String> set = new HashSet<>();
        Set<Object> set2 = new HashSet<>();
        Map<String, Object> map = new HashMap<>();

        for (String resultMap1 : map1.keySet()) {
            for (String resultMap2 : map2.keySet()) {
                set.add(resultMap1);
                set.add(resultMap2);
            }
        }
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
//        System.out.println(list);

        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            Iterator<String> itr = list.iterator();
            String key = entry.getKey();
            Object value = entry.getValue();

            if (map2.containsKey(key)) {
                if (value.equals(map2.get(key))) {
                    map.put(key, map1.get(key));
                } else {
                    map.put(key, new Object[]{value, map1.get(key)});
                }
            }
            if (!map2.containsKey(key)) {
                map.put(key, new Object[]{value});
            }
        }
        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!map1.containsKey(key)) {
                map.put(key, new Object[]{value});
            }
        }
        System.out.println(toString(map));
    }

    public static String toString(Map map) {
        StringBuilder str = new StringBuilder();
        for (Object key : map.keySet()) {
            str.append("  " + key + ": " + map.get(key) + "\n");
        }
        System.out.println(str);
        return str.toString().trim();
    }
}
































