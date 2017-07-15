package ua.nure.sereda.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println(input());
    }

    private static String input() {
        String temp;
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            temp = bufferedReader.readLine();
            while (!temp.isEmpty()) {
                stringBuilder.append(temp);
                stringBuilder.append(System.lineSeparator());
                temp = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
