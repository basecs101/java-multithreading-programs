
class Message {
    String msg;
    boolean isEmpty;

    public Message(String msg, boolean isEmpty) {
        this.msg = msg;
        this.isEmpty = isEmpty;
    }

    public synchronized String read() {
        if (this.isEmpty){
            try {
                System.out.println("Reader is in waiting state!!");
                this.wait();
                /*
                 wait till a message is written by Writer Thread
                 and start executing from next line
                 */
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.isEmpty = true;
        System.out.println("Message Read by Reader : "+this.msg);
        this.msg = "";
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.notifyAll();
        return this.msg ;
    }

    public synchronized void write(String msg) {
        if (!this.isEmpty){
            try {
                this.wait();
                    //wait till there is already a message
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.msg = msg;
        this.isEmpty = false;
        System.out.println("Message Written by Writer : "+this.msg);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.notifyAll();
    }
}

class Reader implements Runnable {
    Message message;
    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String msg = this.message.read();
        while ( !"Finished Writing!!".equals(msg)){
            msg = this.message.read();
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