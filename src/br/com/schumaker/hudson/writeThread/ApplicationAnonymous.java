package br.com.schumaker.hudson.writeThread;

/**
 *
 * @author hudson.schumaker
 */
public class ApplicationAnonymous {
    public static void main(String... args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 80000; i++) {
                System.out.println("Hello: " + i + " Thread: " + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ex) {}
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    }
}