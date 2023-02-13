import java.util.ArrayList;
import java.util.List;

/**
 * Producer -> Produces an item in the buffer
 * Consumer -> Consumes an item from the buffer
 * If this shared buffer is not synchronized properly, there is chance that
 * ArrayIndexOutOfBoundsException may occur.
 * Hence, appropriate synchronized blocks for shared buffer must be used.
 * This solves the problem of exception, but sometimes it may stuck the program
 * and hence Reentrant locks should be used.
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
                System.out.println(Thread.currentThread().getName()+" added "+number);
                synchronized (this.buffer){
                    buffer.add(number);
                }
            }
            System.out.println(Thread.currentThread().getName()+" added "+Main.EOB);
            synchronized (this.buffer){
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
                    continue;
                }
                if (buffer.get(0).equals(Main.EOB)){
                    System.out.println(Thread.currentThread().getName()+" exiting and " +
                            "removing "+ buffer.remove(0));
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName()+ " removed " +buffer.remove(0));
                }
            }
        }
    }
}

public class Main {
    public static final String EOB = "EOB";
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();

        Thread producerThread = new Thread(new Producer(buffer));
        producerThread.setName("producerThread");

        Thread consumerThread1 = new Thread(new Consumer(buffer));
        consumerThread1.setName("consumerThread1");

        Thread consumerThread2 = new Thread(new Consumer(buffer));
        consumerThread2.setName("consumerThread2");



        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}