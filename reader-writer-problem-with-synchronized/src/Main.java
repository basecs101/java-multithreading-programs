
/**
 * A CPU can be dual or quad-core and each can run one thread.
 * Each core has its own cache and this cache is used by the thread that is running
 * in this core.
 * core1 --> Thread1 [core1 cache1]  <-- [Heap(Main Memory)]
 * core2 --> Thread2 [core2 cache2]  <-- [Heap(Main Memory)]
 * Each thread reads message object from heap and stores them into their cache.
 *
 * To solve the problem of local thread cache update issue, we can use
 * volatile keyword with variables that will enforce read/write directly to the
 * main memory and hence all the threads will have same values of volatile variables.
 *
 * volatile vairable can be accessed/updated by many threads at the same time.
 *
 * atomic internally usage volatile but it make sure that operation is atomic and
 * one thread is accessing/updating the atomic at a time
 *
 * Atomic -> volatile + thread safety for variable.
 *
 * Now we are using atomic instead of volatile but the problem still exist where
 * the message written by writer is overridden by itself and hence proper reader-writer
 * functioning is missing
 *
 * If reader thread starts first then it acquires lock and goes into WAITING state
 * and Writer goes into BLOCKED state.
 */
class Message {
    String msg;
    boolean isMsgEmpty;

    public Message(String msg, boolean isMsgEmpty) {
        this.msg = msg;
        this.isMsgEmpty = isMsgEmpty;
    }

    public synchronized String read(){
        while (this.isMsgEmpty);// wait till a message is written by Writer Thread
        this.isMsgEmpty = true;
        return this.msg ;
    }

    public synchronized void write(String msg){
        while (!this.isMsgEmpty);//wait till there is already a message
        System.out.println(this.msg = msg);
        System.out.println(this.isMsgEmpty = false);
    }
}

class Reader implements Runnable {

    Message message;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (String msg = this.message.read();
             !msg.equals("Finished Writing!!") ; msg = this.message.read() ) {
            System.out.println("Message Read by Reader : "+this.message.read());
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

        String[] messages = {"Hello", "How","are","you"};

        for (String msg: messages) {

            this.message.write(msg);
            System.out.println("Message Written by Writer : "+msg);

        }

        this.message.write("Finished Writing!!");

    }
}

public class Main {
    public static void main(String[] args) {
        Message message = new Message("",true);

        Thread readerThread = new Thread(new Reader(message));
        readerThread.setName("ReaderThread");

        Thread writerThread = new Thread(new Writer(message));
        writerThread.setName("WriterThread");
        readerThread.start();
        writerThread.start();
    }
}