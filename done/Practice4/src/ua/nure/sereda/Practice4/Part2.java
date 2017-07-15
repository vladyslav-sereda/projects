package ua.nure.sereda.Practice4;

import java.io.*;
import java.util.Random;

public class Part2 {

    private static final String RAW_DATA = "part2.txt";
    private static final String SORTED_DATA = "part2_sorted.txt";
    private static final int N = 10;
    private static final int MAX = 50;

    public static void main(String[] args) throws IOException {
        int[] data = new int[N];
        randArray(data);
        write(RAW_DATA, data);
        data = read(RAW_DATA);
        System.out.print("input ==> ");
        printArray(data);
        System.out.println();
        sortArray(data);
        write(SORTED_DATA, data);
        read(SORTED_DATA);
        System.out.print("output ==> ");
        printArray(data);
        System.out.println();
    }

    static void randArray(int[] arr) {
        Random rand = new Random();
        for (int iter = 0; iter < arr.length; iter++) {
            arr[iter] = rand.nextInt(MAX + 1);
        }
    }

    static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    static void sortArray(int[] arr) {
        int temp;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static int[] read(String fileName) throws IOException {
        String tempStr;
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Cp1251"))) {
            if ((tempStr = rd.readLine()) == null) {
                throw new IOException("Empty file!!!");
            }
            String[] resStr = tempStr.split(" ");
            int[] res = new int[resStr.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = Integer.parseInt(resStr[i]);
            }
            return res;
        }
    }


    static void write(String fileName, int[] arr) throws IOException {
        try (OutputStreamWriter wr = new OutputStreamWriter(new FileOutputStream(fileName), "Cp1251")) {
            for (int i : arr) {
                wr.append(String.valueOf(i));
                wr.append(" ");
            }
        }
    }
}
