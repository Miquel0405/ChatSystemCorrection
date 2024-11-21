package chatsystem_planasleiman;


import java.net.SocketException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import chatsystem_planasleiman.controller.Controller;
import chatsystem_planasleiman.network.UDPServer;
import chatsystem_planasleiman.ui.View;



public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static final int port = 1789;
    
    public static void main(String[] args) {

        Configurator.setRootLevel(Level.INFO);
        LOGGER.info("Starting chatsystem application");
        
        View.initialize();

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
