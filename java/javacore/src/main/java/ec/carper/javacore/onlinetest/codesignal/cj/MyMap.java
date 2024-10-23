package ec.carper.javacore.onlinetest.codesignal.cj;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyMap {
    public static void main(String[] args) {
       MapOrder(); 
    }

    static void MapOrder(){
        // Create a Map with unsorted data
        Map <Integer, String> unsortedMap = new HashMap<>();
        unsortedMap.put(3, "Alice");
        unsortedMap.put(1, "Bob");
        unsortedMap.put(2, "Charlie");
        unsortedMap.put(4, "David");

        // Create a TreeMap to sort the Map by keys (id in this case)
        Map<Integer, String> sortedMap = new TreeMap<>(unsortedMap);

        // Display the sorted Map
        for (Map.Entry<Integer, String> entry: sortedMap.entrySet()){
            System.out.println("Key: " + entry.getKey() + ", Value: "+entry.getValue());
        }
    }

}
