package ua.nure.sereda.Practice9;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Vladyslav.
 */
class Votes {
    static Map<String, String[]> names= new TreeMap<>();
    static Map<String, Integer> sports= new TreeMap<>();
    static {
        Integer footballVotes = 0;
        Integer biathlonVotes = 0;
        Integer basketballVotes = 0;

        sports.put("Football", footballVotes);
        sports.put("Biathlon", biathlonVotes);
        sports.put("Basketball", basketballVotes);
    }
}
