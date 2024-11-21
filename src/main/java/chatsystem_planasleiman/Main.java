package chatsystem_planasleiman;

import java.io.IOException;

import java.net.SocketException;

import chatsystem_planasleiman.network.UDPMessage;
import chatsystem_planasleiman.network.UDPSender;
import chatsystem_planasleiman.network.UDPServer;



public class Main {
    public static final int port = 1789;
    private static int counter = 0;
    public static void main(String[] args) {
        try{
            UDPServer server = new UDPServer(port);
            server.addObserver(new UDPServer.Observer() {
                @Override
                public void handle(UDPMessage received){
                    System.out.println("received form observer: " + received);
                }    
            });

            server.addObserver(new UDPServer.Observer() {
                @Override
                public void handle(UDPMessage received){
                    counter +=1;
                    System.out.println("Num received: " + counter);
                }    
            });
            server.addObserver(received -> System.out.println("from lamdbda " + received.content()));
            
            server.start();
        } catch (SocketException e){
            System.err.println("Could not start UDPServer :" + e.getMessage());
            System.exit(1);
        }


        try {
            UDPSender.sendLocalhost(port, "HELLO");
            UDPSender.sendLocalhost(port, "HELLO2");
            UDPSender.sendLocalhost(port, "HELLO3");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }  
}
