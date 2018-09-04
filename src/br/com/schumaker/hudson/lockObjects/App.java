package br.com.schumaker.hudson.lockObjects;

/**
 *
 * @author hudson.schumaker
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Synchronized Objects: ");
        Worker worker = new Worker();
        worker.work();
        System.out.println("Synchronized Methods: ");
        WorkerMethodsSynchronized worker2 = new WorkerMethodsSynchronized();
        worker2.work();
    }
}