# Java-Multithreading and Concurrency
---
### Let's explore and learn about multi-threading and concurrency.

## [Multithreading Interview Questions](https://www.digitalocean.com/community/tutorials/java-multithreading-concurrency-interview-questions-answers)
-[x] What is the difference between Process and Thread?
- > ```Program``` - A program is set of instructions(a rule or a statement). Program is passive.
- > ```Process``` - A program under execution is called a Process. Process is active. Processes do not share memory.
- > ```Thread``` - A unit or portion of Process that executes independently and A Process can have many threads running parallely.
  > Multiple Threads share common heap memory of the parent Process. Each Thread has It's own Thread Stack.

- [x] What are the benefits of multithreaded programming?
- > 1. To create high performing applications.
- > 2. Independent tasks can be executed by multiple threads such applications can use multi threading and hence improve performance.
-[x] What is difference between user Thread and daemon Thread? 
  - A daemon thread executes task that is of less priority and a thread is made daemon using thread1.setDaemon(true);
-[x] [How can we create a Thread in Java?](https://medium.com/javarevisited/how-to-create-java-thread-using-thread-and-runnable-2023-14e965474a7)
-[x] What are different states in lifecycle of Thread?
   1. `NEW`
  2. `RUNNABLE`
  3. `TIMED_WAITING`
  4. `WAITING`
  5. `BLOCKED`
  6. `TERMINATED`
-[x] Can we call run() method of a Thread class? 
  - ThreadObject.run(); This will not create a `new` Thread instead will use `main` thread.
-[x] How can we pause the execution of a Thread for specific time? 
  - Using Thread.sleep(milliseconds) pauses currently running thread for specified amt of time but holds the current object lock.
-[x] What do you understand about Thread Priority?
  - Sets the priority for the current thread using method thread.setPriority(int num);
-[x] What is Thread Scheduler and Time Slicing? 
-[x] What is Thread Context-Switching in Multi-Threading?
  - JVM takes out one thread from its execution and saves its TCB and then gives opportunity to another thread to execute shared block/code/method.
-[x] How can we make sure main() is the last thread to finish in Java Program? 
  - Use all thread as user threads and not the daemon thread. Main thread will terminate after executing all user threads.
-[ ] Which is more preferred - Synchronized method or Synchronized block?
-
-[ ] What is Deadlock(explain using Reader/Writer Problem)? How to analyze and avoid deadlock situation?
-[ ] How does threads communicate with each other? 
  - Different threads communicate with each other using `wait()`, `notify()` and `notifyAll()` methods.
-[ ] Why thread communication methods wait(), notify() and notifyAll() are in Object class? 
-[ ] Why wait(), notify() and notifyAll() methods have to be called from synchronized method or block? 
-[ ] Why Thread sleep() and yield() methods are static? 
-[ ] How can we achieve thread safety in Java? 
-[ ] What is volatile keyword in Java 
-[ ] How to create daemon thread in Java? 
-[ ] What is ThreadLocal? 
-[ ] What is Thread Group? Why it’s advised not to use it? 
-[ ] What is Java Thread Dump, How can we get Java Thread dump of a Program? 
-[ ] What is Java Timer Class? How to schedule a task to run after specific interval? 
-[ ] What is Thread Pool? How can we create Thread Pool in Java? 
-[ ] What will happen if we don’t override Thread class run() method?
## [Java Concurrency Interview Questions](https://www.digitalocean.com/community/tutorials/java-multithreading-concurrency-interview-questions-answers)
-[ ] What is atomic operation? What are atomic classes in Java Concurrency API? 
-[ ] What is Lock interface in Java Concurrency API? What are it’s benefits over synchronization? 
-[ ] What is Executors Framework? 
-[ ] What is BlockingQueue? How can we implement Producer-Consumer problem using Blocking Queue? 
-[ ] What is Callable and Future? 
-[ ] What is FutureTask class? 
-[ ] What are Concurrent Collection Classes? 
-[ ] What is Executors Class? 
-[ ] What are some of the improvements in Concurrency API in Java 8?


Write in char streaming and set priority isn't working.