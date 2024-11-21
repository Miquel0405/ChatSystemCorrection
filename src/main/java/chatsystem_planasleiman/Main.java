package chatsystem_planasleiman;

import java.io.IOException;
import java.net.SocketException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import chatsystem_planasleiman.controller.Controller;
import chatsystem_planasleiman.network.UDPMessage;
import chatsystem_planasleiman.network.UDPSender;
import chatsystem_planasleiman.network.UDPServer;



public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static final int port = 1789;
    private static int counter = 0;
    public static void main(String[] args) {

        LOGGER.info("Starting chatsystem application");
        Configurator.setRootLevel(Level.INFO);

        try{
            UDPServer server = new UDPServer(port);

            server.addObserver(msg -> Controller.handleContactDiscoveryMessage(msg));
            server.start();
            
        } catch (SocketException e){
            System.err.println("Could not start UDPServer :" + e.getMessage());
            System.exit(1);
        }
        


       
    }  
}
