package ua.nure.sereda.Practice6.part1;

import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) {
        WordContainer wCont = new WordContainer();
        try (Scanner input = new Scanner(System.in);) {
            String str = "";
            while (!str.equals("stop")) {
                wCont.addWord(str = input.next());
            }
        }
        System.out.println(wCont.toString());
    }

}
