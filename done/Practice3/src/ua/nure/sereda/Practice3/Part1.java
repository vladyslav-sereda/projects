package ua.nure.sereda.Practice3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    // 1     2     3     4   5   6     7
    private final static String REGEXP = "(?m)^(\\S*);(\\S*)(\\s*)(\\S*);((\\S*)@(\\S*))$";
    private final static String REGEXP4 = "(?m)^(.*)$";
    private static Pattern patt = Pattern.compile(REGEXP);
    private static Pattern patt4 = Pattern.compile(REGEXP4);

    public static void main(String[] args) {
        String txt = Util.readFile("part1.txt");
        System.out.println(convert1(txt));
        System.out.println();
        System.out.println(convert2(txt));
        System.out.println();
        System.out.println(convert3(txt));
        System.out.println(convert4(txt));

    }

    public static String convert1(String input) {
        StringBuilder sbuild = new StringBuilder();
        Matcher match = patt.matcher(input);
        while (match.find()) {
            sbuild.append(match.group(1));
            sbuild.append(" ==> ");
            sbuild.append(match.group(5));
            sbuild.append(System.lineSeparator());
        }
        sbuild.deleteCharAt(sbuild.length()-1);
        return sbuild.toString();
    }

    public static String convert2(String input) {
        StringBuilder sbuild = new StringBuilder();
        Matcher match = patt.matcher(input);
        while (match.find()) {
            sbuild.append(match.group(2));
            sbuild.append(match.group(3));
            sbuild.append(match.group(4));
            sbuild.append(" (email: ");
            sbuild.append(match.group(5));
            sbuild.append(")");
            sbuild.append(System.lineSeparator());
        }
        sbuild.deleteCharAt(sbuild.length()-1);
        return sbuild.toString();
    }

    public static String convert3(String input) {
        StringBuilder sbuild = new StringBuilder();
        Matcher match = patt.matcher(input);
        List<String> list = new ArrayList<>();
        boolean firstIter = true;
        while (match.find()) {
            if (!list.contains(match.group(7))) {
                list.add(match.group(7));
            }
        }
        for (String str : list) {
            match = patt.matcher(input);
            sbuild.append(str);
            sbuild.append(" ==> ");
            while (match.find()) {
                if (str.equals(match.group(7))) {
                    if (!firstIter) {
                        sbuild.append(", ");
                    }
                    firstIter = false;
                    sbuild.append(match.group(1));
                }
            }
            sbuild.append(System.lineSeparator());
            firstIter = true;
        }
        sbuild.deleteCharAt(sbuild.length()-1);
        return sbuild.toString();
    }


    public static String convert4(String input) {
        StringBuilder sbuild = new StringBuilder();
        Random rand = new Random();
        Matcher match = patt4.matcher(input);
        System.out.println();
        boolean firstIter = true;
        while (match.find()) {
            if (firstIter) {
                sbuild.append(match.group(1));
                sbuild.append(";Password");
                sbuild.append(System.lineSeparator());
                firstIter = false;
            } else {
                sbuild.append(match.group(1));
                sbuild.append(";");
                sbuild.append(rand.nextInt(8999) + 1000);
                sbuild.append(System.lineSeparator());
            }
        }
        sbuild.deleteCharAt(sbuild.length()-1);
        return sbuild.toString();
    }

}
