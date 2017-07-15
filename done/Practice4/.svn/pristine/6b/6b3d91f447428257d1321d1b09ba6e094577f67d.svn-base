package ua.nure.sereda.Practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

    private static final String BASE_NAME = "resources";

    public static void main(String[] args) {
        String language;
        String key;
        try (Scanner input = new Scanner(System.in, "Cp1251")) {
            while (input.hasNext()) {
                key = input.next();
                if (key.equals("stop")) {
                    break;
                } else {
                    language = input.next();
                    Locale locale = new Locale(language);
                    ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);
                    System.out.println(bundle.getString(key));

                }
            }
        }
    }
}