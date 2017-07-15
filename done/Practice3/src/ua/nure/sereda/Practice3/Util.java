package ua.nure.sereda.Practice3;

import java.io.IOException;
import java.nio.file.*;

public class Util {

    private static final String ENCODING = "Cp1251";

    public static String readFile(String path) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, ENCODING);
        } catch (IOException ex) {
            System.out.println("Exception");
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(readFile("part1.txt"));
    }

}