package chatsystem_planasleiman;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Broadcast {

    /*Function that returns the first valid broadcast address found amond the interfaces of the machine where the program is used*/
    public static InetAddress getBroadcastAddress() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
    
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
    
            // Skip loopback and non-active interfaces
            if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                continue;
            }
    
            for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                InetAddress broadcast = interfaceAddress.getBroadcast();
                
                // Return the first valid broadcast address found
                if (broadcast != null) {
                    return broadcast;
                }
            }
        }
    
        // No broadcast address found
        return null;
    }

    /*Test if getBroadcastAddress returns an address or not */
    public static void main(String[] args) {
        try{
            InetAddress broadcast = getBroadcastAddress();
            if (broadcast == null) {
                System.out.println("Null");
            }
            else {
                System.out.println("Not null");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
