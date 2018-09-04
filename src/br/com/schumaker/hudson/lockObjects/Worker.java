package br.com.schumaker.hudson.lockObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author hudson.schumaker
 */
public class Worker {
    private final Random random = new Random();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();

    public void stageOne() {
        synchronized (lock1) {
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (lock2) {
            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 10000000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void work() {
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            process();
        });

        Thread t2 = new Thread(() -> {
            process();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }
}