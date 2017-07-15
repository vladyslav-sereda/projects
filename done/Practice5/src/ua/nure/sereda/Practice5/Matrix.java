package ua.nure.sereda.Practice5;

import java.util.Random;


class Matrix {
    private int m;
    private int n;
    private int[][] matrix;

    Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        matrix = new int[m][n];
        randInitialisation();
    }

    private void randInitialisation() {
        Random random = new Random();
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                matrix[j][i] = random.nextInt();
            }
        }
    }


    int singleFindMaxValue() {
        int[] temp = new int[m];
        for (int i = 0; i < m; i++) {
            temp[i] = findMaxInMass(matrix[i]);
        }
        return findMaxInMass(temp);
    }

    int parallelFindMaxValue() {
        final int[] tempMass = new int[m];
        Thread[] threads = new Thread[m];
        for (int i = 0; i < m; i++) {
            final int finalI = i;
            threads[i] = new Thread() {
                @Override
                public void run() {
                    tempMass[finalI] = findMaxInMass(matrix[finalI]);
                }
            };
            threads[i].start();
        }
        while (true) {
            boolean finish = true;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    finish = false;
                }
            }
            if (finish) {
                break;
            }
        }
        return findMaxInMass(tempMass);
    }

    private int findMaxInMass(int[] mass) {
        int max = mass[0];
        for (int el : mass) {
            try {
                Thread.sleep(1);
                if (max < el) {
                    max = el;
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        return max;
    }
}
