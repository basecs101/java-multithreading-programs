import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100,true);

        Random random = new Random();
        Runnable producerRunnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    int i = random.nextInt();
                    blockingQueue.add(i);
                    System.out.println("Producer Thread produced : "+i);
                    try {
                        Thread.sleep(random.nextInt(2000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        };


        Thread producer = new Thread(producerRunnable);
        producer.setName("ProducerThread");
        producer.start();

        Runnable consumerRunnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Integer i = blockingQueue.take();
                        System.out.println("Consumer Thread consumed : "+i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Thread.sleep(random.nextInt(2000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread consumer = new Thread(consumerRunnable);
        consumer.setName("ConsumerThread");
        consumer.start();
    }
}