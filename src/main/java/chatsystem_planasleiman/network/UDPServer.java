package chatsystem_planasleiman.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer extends Thread {


    private final DatagramSocket socket;

    public UDPServer(int port) throws SocketException{
        this.socket = new DatagramSocket(port);
    }




    @Override
    public void run() {
        while (true) {
            try{
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + received);
            } catch (IOException e){
                System.err.println("Received error: " + e.getMessage());
            } 
        }
    }
}