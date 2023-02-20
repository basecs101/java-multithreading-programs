import java.util.concurrent.*;

/**
 * Executor provides execute method to handle async tasks. [void execute(Runnable task)]
 * ExecutorService is extended interface of Executor that provides more methods for async tasks.
 * ExecutorService also has shutdown method to terminate all threads and reclaim memory.
 * examples: submit(Runnable/Callable), invokeAny(List of Callable tasks), invokeAll(List of callable tasks).
 * ThreadPoolExecutor --> implementation of ExecutorService and has Worker for mapping thread and tasks.
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(2,
                10,
                5L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run method called");
            }
        };

        Callable<String>  callableTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " call method called");
                return "Hello From call method/task";
            }
        };

        executorService.execute(runnableTask);

        Future<String > fufure = executorService.submit(callableTask);

        System.out.println(fufure.get());

        executorService.shutdown();

    }
}