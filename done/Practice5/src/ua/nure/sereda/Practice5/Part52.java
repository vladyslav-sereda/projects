package ua.nure.sereda.Practice5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Part52 {

    private static final int ITERATION_NUMBER = 3;

    private static final int READERS_NUMBER = 3;

    private static final StringBuilder BUFFER = new StringBuilder();

    private static final int BUFFER_LENGTH = 5;

    private static final int PAUSE = 5;

    private static boolean stop;

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Condition READER_LOCK = LOCK.newCondition();

    private static class Reader extends Thread {
        public void run() {
            LOCK.lock();
            try {
                while (!stop) {
                    try {
                        READER_LOCK.await();
                        read(getName());
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            } finally {
                LOCK.unlock();
            }
        }
    }

    private static class Writer extends Thread {
        public void run() {
            int tact = 0;
            while (!stop) {
                LOCK.lock();
                try {
                    write();
                    READER_LOCK.signalAll();
                } catch (InterruptedException e) {
                    System.out.println(e);
                } finally {
                    LOCK.unlock();
                    try {
                        sleep(PAUSE);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    if (++tact == ITERATION_NUMBER) {
                        stop = true;
                    }
                }
            }
        }

    }

    private static void read(String threadName) throws InterruptedException {
        System.out.printf("Reader %s:", threadName);
        for (int j = 0; j < BUFFER_LENGTH; j++) {
            Thread.sleep(PAUSE);
            System.out.print(BUFFER.charAt(j));
        }
        System.out.println();
        Thread.sleep(5);
    }

    private static void write() throws InterruptedException {
        BUFFER.setLength(0);
        System.err.print("Writer writes:");
        Random random = new Random();
        for (int j = 0; j < BUFFER_LENGTH; j++) {
            Thread.sleep(PAUSE);
            char ch = (char) ('A' + random.nextInt(26));
            System.err.print(ch);
            BUFFER.append(ch);
        }
        System.err.println();
        Thread.sleep(5);
    }

    public static void main(String[] args) throws InterruptedException {
        Writer writer = new Writer();
        List<Thread> readers = new ArrayList<>();
        for (int j = 0; j < READERS_NUMBER; j++) {
            readers.add(new Reader());
        }

        Thread.sleep(10);
        for (Thread reader : readers) {
            reader.start();
        }

        Thread.sleep(10);
        writer.start();

        writer.join();
        for (Thread reader : readers) {
            reader.join();
        }
    }
}
