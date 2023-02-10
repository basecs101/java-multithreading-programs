
class NumberPrinter {

    /**
     * synchronized keyword make sure that one thread executes the method at any given time
     */
     void print() {

         synchronized(this){
             try {
                 for (int i=1; i<=10;i++){
                     Thread.sleep(100);
                     System.out.println(Thread.currentThread().getName() + " "+ i);
                 }
             } catch (InterruptedException e){
                 e.printStackTrace();
             }
         }
         //below statements won't cause thread context switching
         //hence they can be put outside synchronized block

         //statement1
         //statement2
         //statement3

    }
}
/**
 * Create a Thread class so that threads(objects of thread class in runnable state)
 * can execute run method.
 */
class ThreadExample extends Thread {

    NumberPrinter numberPrinter;

    ThreadExample(NumberPrinter numberPrinter){
        this.numberPrinter = numberPrinter;
    }
    @Override
    public void run() {
        System.out.println("Name : "+Thread.currentThread().getName());

        this.numberPrinter.print();

    }
}

public class ThreadDriver {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        NumberPrinter numberPrinter = new NumberPrinter();

        ThreadExample thread1 = new ThreadExample(numberPrinter);
        thread1.setName("Thread-1");
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread1.start();//creates a `new` thread and calls `run` method and thread goes to `runnable` state

        ThreadExample thread2 = new ThreadExample(numberPrinter);
        thread2.setName("Thread-2");
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread2.start();

        ThreadExample thread3 = new ThreadExample(numberPrinter);
        thread3.setName("Thread-3");
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread3.start();


        ThreadExample thread4 = new ThreadExample(numberPrinter);
        thread4.setName("Thread-4");
        thread4.setPriority(4);
        thread4.start();

        System.out.println("Main Thread terminated.");

    }
}