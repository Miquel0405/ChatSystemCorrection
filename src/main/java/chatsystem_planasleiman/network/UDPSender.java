package chatsystem_planasleiman.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
    public static void send (InetAddress addr, int port, String message) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] buff = message.getBytes();
        DatagramPacket packet= new DatagramPacket(buff, buff.length, addr, port);
        socket.send(packet);
        socket.close();
    }


    public static void sendLocalhost( int port, String message) throws IOException {
        UDPSender.send(InetAddress.getLocalHost(), port, message);
    }
}
