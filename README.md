# Java-Multithreading and Concurrency
---
### Let's explore and learn multi-threading and concurrency in Java Programming.

## [Multithreading Interview Questions](https://www.digitalocean.com/community/tutorials/java-multithreading-concurrency-interview-questions-answers)
1. What is the difference between Process and Thread?
   - ```Program``` 
     - A program is set of instructions(a rule or a statement). Program is passive.
   - ```Process``` 
     - A program under execution is called a Process. Process is active. Processes do not share memory.
   - ```Thread```
     - A unit or portion of Process that executes independently and A Process can have many threads running parallely.
     - Multiple Threads share common heap memory of the parent Process. Each Thread has Its own Thread Stack.
2. What are the benefits of multithreaded programming?
   - To create high performing applications.
   - Independent tasks can be executed by multiple threads such applications can use multi threading and hence improve performance.
3. What is difference between user Thread and daemon Thread? 
   - A daemon thread executes task that is of less priority and a thread is made daemon using thread1.setDaemon(true);
4. [How can we create a Thread in Java?](https://medium.com/javarevisited/how-to-create-java-thread-using-thread-and-runnable-2023-14e965474a7)

5. What are different states in lifecycle of Thread? 
   1. `NEW`
   2. `RUNNABLE`
   3. `TIMED_WAITING`
   4. `WAITING`
   5. `BLOCKED`
   6. `TERMINATED`
6. Can we call run() method of a Thread class? 
   - ThreadObject.run(); This will not create a `new` Thread instead will use `main` thread.
7. How can we pause the execution of a Thread for specific time? 
   - Using Thread.sleep(milliseconds) pauses currently running thread for specified amt of time but holds the current object lock.
8. What do you understand about Thread Priority?
   - Sets the priority for the current thread using method thread.setPriority(int num);
9. What is Thread Scheduler and Time Slicing? 
10. What is Thread Context-Switching in Multi-Threading?
    - JVM takes out one thread from its execution and saves its TCB and then gives opportunity to another thread to execute shared block/code/method.
11. How can we make sure main() is the last thread to finish in Java Program? 
    - Use all thread as user threads and not the daemon thread. Main thread will terminate after executing all user threads.
12. Which is more preferred - Synchronized method or Synchronized block?
13. What is Deadlock(explain using Reader/Writer Problem)? How to analyze/detect and avoid deadlock situation?
14. How does threads communicate with each other? 
    - Different threads communicate with each other using `wait()`, `notify()` and `notifyAll()` methods.
15. Why thread communication methods wait(), notify() and notifyAll() are in Object class? 
16. Why wait(), notify() and notifyAll() methods have to be called from synchronized method or block? 
17. Why Thread sleep() and yield() methods are static? 
18. How can we achieve thread safety in Java? 
19. What is volatile keyword in Java 
20. What is ThreadLocal? 
21. What is Thread Group? Why it’s advised not to use it? 
22. What is Java Thread Dump, How can we get Java Thread dump of a Program? 
23. What is Java Timer Class? How to schedule a task to run after specific interval? 
24. What is Thread Pool? How can we create Thread Pool in Java? 
25. What will happen if we don’t override Thread class run() method?


## [Java Concurrency Interview Questions](https://www.digitalocean.com/community/tutorials/java-multithreading-concurrency-interview-questions-answers)

1. What is atomic operation? What are atomic classes in Java Concurrency API? 
2. What is Lock interface in Java Concurrency API? What are its benefits over synchronization? 
3. What is Executors Framework? 
4. What is BlockingQueue? How can we implement Producer-Consumer problem using Blocking Queue? 
5. What is Callable and Future? 
6. What is FutureTask class? 
7. What are Concurrent Collection Classes? 
8. What is Executors Class? 
9. What are some improvements in Concurrency API in Java 8?


Writing in char streaming and set priority isn't working.