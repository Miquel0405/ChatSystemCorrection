package chatsystem_planasleiman;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiverThread extends Thread {
    private DatagramSocket socket;
    private boolean running;
    private ThreadManager manager;

    
    public ReceiverThread(DatagramSocket socket, ThreadManager manager) {
        this.socket = socket;
        this.manager = manager;
        this.running = true;
    }

    

    public ThreadManager getManager() {
        return manager;
    }


    /*Waiting for a message */
    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        while (running) {
            try {
                System.out.println("Waiting...");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Wait for a packet
                InetAddress senderAdress = packet.getAddress();
                InetAddress localAddress = getManager().getLocalAddress();

                /*If the machine receives a message from itself, it ingores it*/
                if (senderAdress.equals(localAddress)){
                    continue;
                }
                else{
                    String received = new String(packet.getData(), 0, packet.getLength());
                    // Notify the manager (or directly update the model)
                    manager.handleReceivedMessage(received, packet.getAddress());
                }
            } catch (Exception e) {
                if (running) e.printStackTrace(); // Ignore exceptions if stopping
            }
        }
        socket.close();
    }


    /*Stop the thread */
    public void stopReceiver() {
        running = false;
        socket.close();
    }

}