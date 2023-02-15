
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