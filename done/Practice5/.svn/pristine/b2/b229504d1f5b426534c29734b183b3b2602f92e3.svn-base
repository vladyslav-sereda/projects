package ua.nure.sereda.Practice5;

public class Part3{

    public static void main(String[] args) throws InterruptedException {
        final Counters count = new Counters();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                long time;
                public void run() {
                    time = System.currentTimeMillis();
                    while ((System.currentTimeMillis() - time) <= 1000) {
                        count.addFirstCounter();
                        try {
                            sleep(10);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        count.addSecondCounter();
                    }
                }
            }.start();
        }
        Thread.sleep(1001);
        System.out.println("Not synchronized ==> " + count.getFirstCounter() + " == " + count.getSecondCounter());

        final Counters syncCount = new Counters();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                long time;
                public void run() {
                    time = System.currentTimeMillis();
                    while ((System.currentTimeMillis() - time) <= 1000) {
                        syncCount.addFirstSyncCounter();
                        try {
                            sleep(10);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        syncCount.addSecondSyncCounter();
                    }
                }
            }.start();
        }
        Thread.sleep(1001);
        System.out.println("Synchronized ==> " + syncCount.getFirstCounter() + " == " + syncCount.getSecondCounter());
    }
}