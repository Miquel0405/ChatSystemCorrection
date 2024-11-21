package chatsystem_planasleiman.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class UDPServer extends Thread {

    /*Interface that observers of the UDOServer must implement */
    public interface Observer {
        /*Method called each time a message is received */
        void handle(UDPMessage received);
    }


    private final DatagramSocket socket;
    private List<Observer> observers = new ArrayList<>();

    public UDPServer(int port) throws SocketException{
        this.socket = new DatagramSocket(port);
    }

    public void addObserver(Observer obs){
        synchronized (this.observers){
            this.observers.add(obs);
        }
    }
    




    @Override
    public void run() {
        while (true) {
            try{
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                UDPMessage message = new UDPMessage(received, packet.getAddress());

                synchronized (this.observers){
                    for (Observer obs : this.observers){
                        obs.handle(message);
                    }
                }
            } catch (IOException e){
                System.err.println("Received error: " + e.getMessage());
            } 
        }
    }
}