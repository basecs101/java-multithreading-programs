import java.util.ArrayList;
import java.util.List;

/**
 * Producer -> Produces an item in the buffer
 * Consumer -> Consumes an item from the buffer
 * If the shared buffer is not synchronized properly, there is chance that
 * ArrayIndexOutOfBoundsException may occur.
 * Hence, appropriate synchronized blocks for shared buffer must be used.
 * This solves the problem of exception, but sometimes a thread may go in
 * long waiting for acquiring lock on the buffer, such threads are starved(waiting for the lock)
 * Solution for the starvation caused by synchronized is Reentrant lock, and it should be used.
 */
class Producer implements Runnable{
    private final List<String> buffer;

    public Producer(List<String> buffer) {
        this.buffer = buffer;
    }


    @Override
    public void run() {
        String[] numbers = {"1","2","3"};
            for (String number : numbers){
                Main.waitForMillis(Main.MILLIS);
                System.out.println(Thread.currentThread().getName()+" added "+number);
                synchronized (this.buffer){
                    buffer.add(number);
                }
            }
            System.out.println(Thread.currentThread().getName()+" added "+Main.EOB);
            synchronized (this.buffer){
                Main.waitForMillis(Main.MILLIS);
                buffer.add(Main.EOB);
            }
    }
}
class Consumer implements Runnable{
    private final List<String> buffer;

    public Consumer(List<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        while (true) {

            synchronized (this.buffer) {
                if (buffer.isEmpty()){

                    Main.waitForMillis(Main.MILLIS);
                    System.out.println(Thread.currentThread().getName()+" buffer is empty");
                    continue;
                }
                if (buffer.get(0).equals(Main.EOB)){
                    Main.waitForMillis(Main.MILLIS);
                    System.out.println(Thread.currentThread().getName()+" exiting");
                    break;
                } else {
                    Main.waitForMillis(Main.MILLIS);
                    System.out.println(Thread.currentThread().getName()+ " removed " +buffer.remove(0));
                }
            }
        }
    }
}

public class Main {
    public static final String EOB = "EOB";
    public static final long MILLIS = 100;

    public static void waitForMillis(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();

        Thread producerThread = new Thread(new Producer(buffer));
        producerThread.setName("Producer-Thread");

        Thread consumerThread1 = new Thread(new Consumer(buffer));
        consumerThread1.setName("Consumer-Thread1");

        Thread consumerThread2 = new Thread(new Consumer(buffer));
        consumerThread2.setName("Consumer-Thread2");

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}