package ua.nure.sereda.Practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String FILE_NAME = "part1.txt";
    private static final String ENCODING = "Cp1251";
    private static final String REGEXP = "(?U)(\\w+)";

    public static void main(String[] args) throws IOException {
        BufferedReader txt = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), ENCODING));
        StringBuilder sbild = new StringBuilder();
        String string;
        while ((string = txt.readLine()) != null) {
            sbild.append(string);

            sbild.append(System.lineSeparator());
        }
        txt.close();
        System.out.print(convert(sbild.toString()));
    }

    static String convert(String input) {
        String res = input;
        Pattern patt = Pattern.compile(REGEXP);
        Matcher match = patt.matcher(input);
        while (match.find()) {
            if (match.group(1).length() > 3) {
                res = res.replace(match.group(1), match.group(1).toUpperCase());
            }
        }
        return res;
    }
}