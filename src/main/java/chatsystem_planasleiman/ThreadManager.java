package chatsystem_planasleiman;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ThreadManager{
    private ReceiverThread receiverThread;
    private SenderThread senderThread;
    private DatagramSocket socket;
    private Queue<Message> messageQueue = new LinkedList<>();
    private User user;
    private InetAddress localAddress;

    private static String greeting_msg = "Hello i am a new user";

    private static int port = 1789;

    public ThreadManager(User user, InetAddress ipAddress) throws SocketException {
        this.user = user;
        this.localAddress = ipAddress;
        socket = new DatagramSocket(port);    // Single socket for both threads per user

        //Creates both threads
        receiverThread = new ReceiverThread(socket, this);
        senderThread = new SenderThread(socket, port, this);
    }


    //Getters
    public InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public User getUser() {
        return user;
    }

    public DatagramSocket getSocket() {
        return socket;
    }



    //Starts both threads
    public void startThreads() {
        receiverThread.start();
        senderThread.start();
    }

    //Stops both threads
    public void stopThreads() {
        receiverThread.stopReceiver();
        senderThread.stopSender();
    }



    // Method to add message to be sent to the msg Queue
    public void sendMessage(String content, InetAddress addr) {
        synchronized (messageQueue) {
            messageQueue.add(new Message(content, addr));
        }
    }

    //SenderThread retrieves the next message to send
    public Message getNextMessage() {
        synchronized (messageQueue) {
            return messageQueue.poll();
        }
    }

    

    //Connects a new user to the network
    public void connect(){
        try {
            String greeting_msg = "Hello i am a new user";
            byte[] buf = greeting_msg.getBytes();

            // Broadcasting the first message to connect to the network (the message should be received by all the other machines in the network)
            DatagramPacket packet = new DatagramPacket(buf, buf.length, Broadcast.getBroadcastAddress(), port);
            getSocket().setBroadcast(true);
            getSocket().send(packet);
            System.out.println("New connection" + "[" +Thread.currentThread().getName() + "]\n");


            //Ask the user to select a Username (that is not in its contact list)
            if (getUser().getUsername() == null){

                // Choosing a username
                getUser().SetUsername();

                //Sending the new username to all the ipAdresses in the machine's contact list
                broadcastUsername();
            } 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Function to send a user's username to all the ipAdresses in its contact list*/
    public void broadcastUsername() {
        try {
            for (Map.Entry<String, InetAddress> contact :getUser().getContList().getContacts().entrySet()) {
                String contactName = contact.getKey();
                InetAddress contactAddress = contact.getValue();

                // Add a new msg to the sending queue
                sendMessage(contactName, contactAddress);
            }

        } catch (Exception e) {
            System.out.println("Contactlist Vide");
        }
    }






    // To handle received messages
    public void handleReceivedMessage(String received, InetAddress addr) {
        System.out.println("[" + user.getUsername() + "] Received message: " + received + " from " + addr +" [" +Thread.currentThread().getName() + "]\n");
        if (received.equals(greeting_msg)) {
            System.out.println("New user detected" + "[" +Thread.currentThread().getName() + "]\n");

            //Send the username of this.user
            try {
                //Add a message with the user name and the sender addr to the msg queue
                sendMessage(getUser().getUsername(), addr);
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("New username: " + received + " received and added to contact list\n");
            user.getContList().addContact(received, addr);
            user.getContList().printContactList();
        }
    }   
}