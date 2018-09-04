package br.com.schumaker.hudson.writeThread;

/**
 *
 * @author hudson.schumaker
 */
class RunnerRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            System.out.println("Hello: " + i + " Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {}
        }
    }
}

public class ApplicationRunnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnerRunnable());
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        
        new Thread(new RunnerRunnable()).start();
    }
}