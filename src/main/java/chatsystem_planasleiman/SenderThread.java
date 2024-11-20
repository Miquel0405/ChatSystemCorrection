package chatsystem_planasleiman;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SenderThread extends Thread {
    private DatagramSocket socket;
    private boolean running;
    private int port;
    private ThreadManager manager;

    public SenderThread(DatagramSocket socket, int port, ThreadManager manager) {
        this.socket = socket;
        this.port = port;
        this.manager = manager;
        this.running = true;
    }


    /*Getters */
    public ThreadManager getManager() {
        return this.manager;
    }

    public DatagramSocket getSocket() {
        return this.socket;
    }

    public int getPort() {
        return port;
    }





    @Override
    public void run() {
        //First connection to the network (Check ThreadManager to see the function)
        getManager().connect();
        
        //Checks if there is any message that has to be sent by checking the message queue 
        while (running) {
            Message message = manager.getNextMessage();

            /*If ther is a message to be sent then it sends the content to the specific address*/
            if (message != null) {
                try {
                    byte[] buffer = message.getContent().getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, message.getRecipientAddress(), getPort());
                    socket.send(packet);
                    System.out.println("Sent message: " + message + "[" +Thread.currentThread().getName() + "]\n");
                } catch (Exception e) {
                    /*If the content of the message to be sent is null then... */
                    System.out.println("Rien a envoyer");

                }
                
            }
            try {
                Thread.sleep(1000); // Sleep or wait for a trigger
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        socket.close();
    }

    public void stopSender() {
        running = false;
        socket.close();
    }
}