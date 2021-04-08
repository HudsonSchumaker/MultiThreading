package br.com.schumaker.hudson.produceConsumer;

/**
 *
 * @author Hudson Schumaker
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();
        Runnable r1 = () -> {
            try {
                processor.produce();
            } catch (InterruptedException ex) {}
        };
        new Thread(r1).start();
        
        Runnable r2 = () -> {
            try {
                processor.consume();
            } catch (InterruptedException ex) {}
        };
        new Thread(r2).start();
        
        // Pause for 30 seconds and force quitting the app 
        // because we're looping infinitely
        Thread.sleep(30000);
        System.exit(0);
    } 
}
