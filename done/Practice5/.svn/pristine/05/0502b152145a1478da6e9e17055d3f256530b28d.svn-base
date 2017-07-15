package ua.nure.sereda.Practice5;

public class Part4 {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(4, 100);
        long time = System.currentTimeMillis();
        matrix.parallelFindMaxValue();
        System.out.println("Parallel find max value ==> " + (System.currentTimeMillis() - time) + " ms");
        time = System.currentTimeMillis();
        matrix.singleFindMaxValue();
        System.out.println("Single find max value ==> " + (System.currentTimeMillis() - time) + " ms");
    }
}
