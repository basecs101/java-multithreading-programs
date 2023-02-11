/**
 * What Is a Deadlock?
 * Deadlock occurs when multiple threads need the same locks but obtain these locks in a different order.
 * A Java multithreaded program may suffer from the deadlock condition because the synchronized keyword causes the executing thread to block while waiting for the lock associated with the specified object.
 * Deadlock is a condition where the threads are waiting infinitely for the resources(locks).
 */
public class DeadLockDriver {

    public static void main(String[] args) {

        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread1 thread1 = new Thread1(lock1,lock2);
        thread1.setName("Thread-1");
        thread1.start();
        Thread2 thread2 = new Thread2(lock1,lock2);
        thread2.setName("Thread-2");
        thread2.start();
    }
}

class Thread1 extends Thread {

    final Object lock1;
    final Object lock2;

    Thread1(Object lock1, Object lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1){
            System.out.println("Thread-1 is running and has acquired lock1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread-1 is running and waiting for lock2");
            synchronized (lock2){
                System.out.println("Thread-1 is running and has acquired lock2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Thread-1 is releasing lock2");
        }
        System.out.println("Thread-1 is releasing lock1");
    }
}

class Thread2 extends Thread {

    final Object lock1;
    final Object lock2;

    Thread2(Object lock1, Object lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }
    @Override
    public void run() {
        synchronized (lock2){
            System.out.println("Thread-2 is running and has acquired lock2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread-2 is running and waiting for lock1");
            synchronized (lock1){
                System.out.println("Thread-2 is running and has acquired lock1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Thread-2 is releasing lock1");
        }
        System.out.println("Thread-2 is releasing lock2");
    }
}