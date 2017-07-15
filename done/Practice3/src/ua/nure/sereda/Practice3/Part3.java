package ua.nure.sereda.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private final static String REGEXP = "(?U)[^\\S]((\\p{javaLowerCase})(\\w+))";
    private static Pattern patt = Pattern.compile(REGEXP);

    public static void main(String[] args) {
        String txt = Util.readFile("part3.txt");
        System.out.println(convert(txt));
    }

    public static String convert(String input) {
        String res = input;
        Matcher match = patt.matcher(input);
        while (match.find()) {
            res = res.replace(match.group(1), match.group(2).toUpperCase() + match.group(3));
        }
        return res;
    }
}
