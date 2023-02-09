
class NumberPrinter2 {
    /**
     * synchronized keyword make sure that one thread executes the method at any given time
     */
    synchronized void print() throws InterruptedException {
        for (int i=1; i<=10;i++){
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " "+ i);
        }
    }
}

class ThreadExample2 implements Runnable {

    NumberPrinter2 numberPrinter;
    public ThreadExample2(NumberPrinter2 numberPrinter) {
        this.numberPrinter = numberPrinter;
    }

    @Override
    public void run() {
        try {
            this.numberPrinter.print();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ThreadDriver2 {

    public static void main(String[] args) {

        NumberPrinter2 numberPrinter = new NumberPrinter2();

        ThreadExample2 threadExample1 = new ThreadExample2(numberPrinter);
        Thread thread1 = new Thread(threadExample1);
        thread1.setName("Thread-1");
        thread1.start();

        ThreadExample2 threadExample2 = new ThreadExample2(numberPrinter);
        Thread thread2 = new Thread(threadExample2);
        thread2.setName("Thread-2");
        thread2.start();

    }
}
