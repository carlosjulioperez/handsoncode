package ec.carper.javacore.testing;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class VisitCounterA {

    // PEPA (pero no valida nulos)
    // public Map<Long, Long> count(Map<String, UserStats>... map) {
    //     Map<Long, Long> resultCount = 
    //         Arrays.stream(map).
    //         filter(Objects::nonNull).flatMap(m -> m.entrySet().stream())
    //             .map(e -> new SimpleEntry<Long, Long>(Long.valueOf(e.getKey()),
    //                     e.getValue().getVisitCount().orElse(0L)))
    //             .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1 + v2));
    //     //System.out.println("return value: " + resultCount);
    //     return resultCount;
    // }

    public Map<Long, Long> count(Map<String, UserStats>... visits) {
        if (Objects.isNull(visits)) return Collections.emptyMap();

        Map<Long,Long> resultCount = new HashMap<Long,Long>();
        if(visits != null){
            resultCount =
            Arrays.stream(visits)
            .filter(Objects::nonNull)
            .flatMap(e -> getUserCountMap(e).entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey,
                                      Map.Entry::getValue,
                                      (v1,v2)->v1+v2));
        }
        return resultCount;
    }

    public Map<Long, Long> getUserCountMap(Map<String, UserStats> map) {
        Map<Long, Long> resultCount = new HashMap<Long, Long>();

        map.forEach((k, v) -> {
            try {
                String key = (String) k;
                UserStats userValue = (UserStats) v;
                Long userId = new Long(key);
                // System.out.println("key :::"+key+":::"+resultCount.getOrDefault(userId, 0l));
                Optional<Long> count = userValue.getVisitCount();
                // System.out.println(count.get());
                count.ifPresent(aLong -> resultCount.put(userId, (resultCount.getOrDefault(userId, 0l) + aLong)));
                // System.out.println(resultCount);
            } catch (Exception e) {
            }
        });
        //System.out.println("ret " + resultCount);
        return resultCount;
    }

    public static void main(String[] args) {

        VisitCounterA v = new VisitCounterA();
        Map<String, UserStats> map1 = new HashMap<String, UserStats>() {{
            put("101", new UserStats(Optional.of(10L) ));
            put("102", new UserStats(Optional.of( 1L) ));
        }};

        Map<String, UserStats> map2 = new HashMap<String, UserStats>() {{
            put("101", new UserStats(Optional.of(10L) ));
            put("102", new UserStats(Optional.of( 1L) ));
            put("103", null); //this should not cause error
            put("ABC", new UserStats(Optional.of( 1L) )); //this should not cause error
        }};

        Map<String, UserStats> map3 = new HashMap<String, UserStats>() {{
            put("101", new UserStats(Optional.of(10L) ));
            put("102", new UserStats(Optional.of( 1L) ));
        }};
        Map<String, UserStats> msNull = null;

        Map<Long, Long> result = v.count(map1, map2, map3, msNull);

        // Map map1 = new HashMap<String, UserStats>();
        // map1.put("001", new UserStats(Optional.of(5L)) );
        // map1.put("002", new UserStats(Optional.of(15L)) );
        // map1.put("003", new UserStats(Optional.of(20L)) );
        // Map<Long, Long> result = v.count(map1);

        System.out.println(result);

    }
}
