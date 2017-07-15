package ua.nure.sereda.Practice5;

import static java.lang.Thread.sleep;

class Part1Runnable implements Runnable {
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            try {
                sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
