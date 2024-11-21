package chatsystem_planasleiman.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chatsystem_planasleiman.contacts.ContactList;
import chatsystem_planasleiman.network.UDPMessage;

public class ControllerTest {

    @BeforeEach
    void clearContactList(){
        ContactList.getInstance().clear();
    }


    @Test
    void messageHandlingTest() throws UnknownHostException{
        ContactList contacts = ContactList.getInstance();
        UDPMessage msg1 = new UDPMessage("alice", InetAddress.getByName("10.5.5.10"));
        UDPMessage msg2 = new UDPMessage("bob", InetAddress.getByName("10.5.5.11"));

        assert !contacts.hasUserName("alice");
        Controller.handleContactDiscoveryMessage(msg1);
        assert contacts.hasUserName("alice");
        
        assert !contacts.hasUserName("bob");
        Controller.handleContactDiscoveryMessage(msg2);
        assert contacts.hasUserName("bob");

        Controller.handleContactDiscoveryMessage(msg2);

    }
}
