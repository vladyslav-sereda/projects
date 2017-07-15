package ua.nure.sereda.Practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String FILE_NAME = "part3.txt";
    private static final String ENCODING = "Cp1251";
    private static final String REGEXP_CHAR = "(?U)(\\b[\\w&&\\D]\\b)";
    private static final String REGEXP_STRING = "(?U)(\\b\\p{L}{2,}\\b)";
    private static final String REGEXP_INT = "\\s(\\d+)\\s";
    private static final String REGEXP_DOUBLE = "(\\d*\\.\\d*)";

    public static void main(String[] args) throws IOException {
        String txt = readTxt(FILE_NAME);
        try (Scanner input = new Scanner(System.in, ENCODING)) {
            String opt;
            while (input.hasNext()) {
                opt = input.nextLine();
                switch (opt) {
                    case "char":
                        printType(txt, Pattern.compile(REGEXP_CHAR));
                        break;
                    case "String":
                        printType(txt, Pattern.compile(REGEXP_STRING));
                        break;
                    case "int":
                        printType(txt, Pattern.compile(REGEXP_INT));
                        break;
                    case "double":
                        printType(txt, Pattern.compile(REGEXP_DOUBLE));
                        break;
                    case "stop":
                        return;
                    default:
                        break;
                }
            }
        }
    }

    static String readTxt(String fileName) throws IOException {
        BufferedReader txt = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), ENCODING));
        StringBuilder sbild = new StringBuilder();
        String string;
        while ((string = txt.readLine()) != null) {
            sbild.append(string);
            sbild.append(System.lineSeparator());
        }
        txt.close();
        return sbild.toString();
    }

    static void printType(String txt, Pattern patt) {
        Matcher match = patt.matcher(txt);
        while (match.find()) {
            System.out.print(match.group(1) + " ");
        }
        System.out.println();
    }
}