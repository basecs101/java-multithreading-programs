import java.util.Random;

class Message {
    String message;
    boolean empty = true;

    //Method used by reader
    public synchronized String read() {
        while (empty) {
            try {
                /*
                 Reader thread waits until Writer invokes the notify()
                 method or the notifyAll() method for 'message' object.
                 Reader thread releases ownership of lock and waits
                 until Writer thread notifies Reader thread waiting on
                 this object's lock to wake up either through a call to
                 the notify method or the notifyAll method.
                 */
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "Interrupted.");
            }
        }
        empty = true;//Reader reads the message and marks empty as true.
        /*
         Wakes up all threads that are waiting on 'message' object's monitor(lock).
         This thread(Reader) releases the lock for 'message' object.
         */
        notifyAll();
        return message;//Reader reads the message.
    }

    //Method used by writer
    public synchronized void write(String message) {
        while (!empty) {
            try {
                /*
                 Writer thread waits until Reader invokes the notify()
                 method or the notifyAll() method for 'message' object.
                 Writer thread releases ownership of lock and waits
                 until Reader thread notifies Writer thread waiting on
                 this object's lock to wake up either through a call to
                 the notify method or the notifyAll method.
                 */
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "Interrupted.");
            }
        }
        this.message = message;//Writer writes the message.
        empty = false;//Now make empty as false.
        /*
         Wakes up all threads that are waiting on 'message' object's monitor(lock).
         This thread(Writer) releases the lock for 'message' object.
         */
        notifyAll();
    }
}

class Reader implements Runnable {
    Message message;
    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String latestMessage = message.read(); !"Finished!".equals(latestMessage); latestMessage = message.read()) {
            System.out.println("Reader read : "+latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                System.out.println("Reader Thread Interrupted!!!");
            }
        }
    }
}

class Writer implements Runnable {
    Message message;
    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        String[] messages = {
                "Humpty Dumpty sat on a wall",
                "Humpty Dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"
        };

        Random random = new Random();

        for (String msg : messages) {
            message.write(msg);
            System.out.println("Writer wrote : "+ msg);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                System.out.println("Writer Thread Interrupted!!!");
            }
        }
        message.write("Finished!");
    }
}

public class Main {
    public static void main(String[] args) {
        Message message = new Message();

        Thread readerThread = new Thread(new Reader(message));
        readerThread.setName("ReaderThread");

        Thread writerThread = new Thread(new Writer(message));
        writerThread.setName("WriterThread");

        readerThread.start();
        writerThread.start();
    }
}