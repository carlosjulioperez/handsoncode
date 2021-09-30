package ec.carper.javacore.testing;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class VisitCounter {

    class UserStats {

        private Optional<Long> visitCount;

        UserStats(Optional<Long> visitCount) {
            this.visitCount = visitCount;
        }

        Optional<Long> getVisitCount() {
            return this.visitCount;
        }
    }
    
    public static void main(String [] args){

        VisitCounter v = new VisitCounter();
        Map<String, VisitCounter.UserStats> ms1 = new HashMap<String, VisitCounter.UserStats>() {{
            put("100001", new v.UserStats(Optional.of(10L) ));
            put("100002", new v.UserStats(Optional.of( 1L) ));
        }};
        Map<String, VisitCounter.UserStats> ms2 = new HashMap<String, VisitCounter.UserStats>() {{
            put("100001", new v.UserStats(Optional.of(10L) ));
            put("100002", new v.UserStats(Optional.of( 1L) ));
            put("100003", null);//this should not cause error
            put("ABCDEF", new v.UserStats(Optional.of( 1L) ));//this should not cause error
        }};
        Map<String, VisitCounter.UserStats> ms3 = new HashMap<String, VisitCounter.UserStats>() {{
            put("100001", new v.UserStats(Optional.of(10L) ));
            put("100002", new v.UserStats(Optional.of( 1L) ));
        }};
        Map<String, VisitCounter.UserStats> msNull = null;

        Map<Long, Long> result = v.count(ms1, ms2, ms3,msNull);
        System.out.println(result);

    }

    public Map<Long, Long> count(Map<String, UserStats>... visits) {
        if (Objects.isNull(visits)) return Collections.emptyMap();

        Map<Long, Long> result = Arrays.stream(visits)
            .filter(Objects::nonNull)//take out null maps
            .flatMap(m -> m.entrySet().parallelStream()) // just unpack to entry set . Hey use multi-core!
            .filter( entry -> isValidUserId(entry.getKey())) // is key valid , not null, Long parsable
            .filter( entry -> isValiduserStats(entry.getValue())) // is key valid , not null, Long parsable
            .map( entry -> new AbstractMap.SimpleEntry<Long, Long>(Long.parseLong(entry.getKey()), entry.getValue().getVisitCount().get())) // convert to Long, Long
            .collect(groupingBy(Map.Entry::getKey, summingLong(Map.Entry::getValue)));
        return result; //test
    }

    boolean isValidUserId(String key){
        if (Objects.isNull(key)) return false; // null filter
        try{
            Long.parseLong(key);
        }catch( Exception e){
            return false; // not long, filter
        }
        return true;
    }
    boolean isValiduserStats(UserStats stat){
        if (Objects.isNull(stat)) return false;  //null filter
        if (!stat.getVisitCount().isPresent()) return false; // null value filter
        return true;
    }    

}
