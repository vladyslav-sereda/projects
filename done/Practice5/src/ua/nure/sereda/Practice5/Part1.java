package ua.nure.sereda.Practice5;

import static java.lang.Thread.sleep;

public class Part1 {

    public static void main(String[] args) {
        Thread firstThread = new Thread(new Part1Thread());
        firstThread.start();
        try {
            sleep(5000);
            firstThread.interrupt();
            firstThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        Thread secondThread = new Thread(new Part1Runnable());
        secondThread.start();
        try {
            sleep(5000);
            secondThread.interrupt();
            secondThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

