package ua.nure.sereda.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private final static String REGEXP = "(?U)(\\w+)";
    private static Pattern patt = Pattern.compile(REGEXP);

    public static void main(String[] args) {
        String txt = Util.readFile("part2.txt");
        System.out.println(convert(txt));

    }


    public   static String convert(String input) {
        StringBuilder sbuildMin = new StringBuilder();
        StringBuilder sbuildMax = new StringBuilder();
        sbuildMin.append("Min: ");
        sbuildMax.append("Max: ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean firstIterMin = true;
        boolean firstIterMax = true;
        Matcher match = patt.matcher(input);
        while (match.find()) {
            if (min > match.group(1).length()) {
                min = match.group(1).length();
            }
            if (max < match.group(1).length()) {
                max = match.group(1).length();
            }
        }
        match = patt.matcher(input);
        while (match.find()) {
            if (min == match.group(1).length() && !sbuildMin.toString().contains(match.group(1))) {
                if (!firstIterMin) {
                    sbuildMin.append(", ");
                }
                sbuildMin.append(match.group(1));
                firstIterMin = false;
            } else if (max == match.group(1).length() && !sbuildMax.toString().contains(match.group(1))) {
                if (!firstIterMax) {
                    sbuildMax.append(", ");
                }
                sbuildMax.append(match.group(1));
                firstIterMax = false;
            }
        }
        sbuildMin.append(System.lineSeparator());
        return sbuildMin.toString() + sbuildMax.toString();
    }
}
