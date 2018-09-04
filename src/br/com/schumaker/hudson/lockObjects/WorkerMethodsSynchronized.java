package br.com.schumaker.hudson.lockObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;


/**
 *
 * @author hudson.schumaker
 */
public class WorkerMethodsSynchronized {
    private final Random random = new Random();
    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();

    /**
     * synchronized, methods use different data (list1 list2) so by synchronized
     * methods if one thread runs the stageOne other thread cannot run stageTwo
     * at the same time because that same locks are used. Solution is using two
     * lock Object for two shared data.
     */
    public synchronized void stageOne() {
        try {
            //do your work here
            Thread.sleep(1);
        } catch (InterruptedException ex) {}
        list1.add(random.nextInt(1000));
    }

    public synchronized void stageTwo() {
        try {
            //do your work here
            Thread.sleep(1);
        } catch (InterruptedException ex) {}
        list2.add(random.nextInt(1000));
    }
    
    public synchronized void stageThree() {
       //list1.sort((l1,l2) -> l1.compareTo(l2)); // Versão longa
       list1.sort(comparing(l1 -> l1));
       list2.sort(comparingInt(l2 -> l2));// use assim para evitar autoboxing (long, doudle e int)
    }

    public void process() {
        for (int i = 0; i < 100; i++) {
            stageOne();
            stageTwo();
            stageThree();
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
        
        Thread t3 = new Thread(() -> {
            process();
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex) {}
        long end = System.currentTimeMillis();
        
        list1.forEach(l -> System.out.println(l));
        list2.forEach(l -> System.out.println(l));
        
        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }
}