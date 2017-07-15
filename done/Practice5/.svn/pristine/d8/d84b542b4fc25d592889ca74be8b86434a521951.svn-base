package ua.nure.sereda.Practice5;

class Part1Thread extends Thread{
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
