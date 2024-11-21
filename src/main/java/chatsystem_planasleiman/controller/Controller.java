package chatsystem_planasleiman.controller;


import chatsystem_planasleiman.contacts.ContactAlreadyExists;
import chatsystem_planasleiman.contacts.ContactList;
import chatsystem_planasleiman.network.UDPMessage;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);


    public static void handleContactDiscoveryMessage(UDPMessage message){
        ContactList contacts = ContactList.getInstance();
        try {
            contacts.addUser(message.content());
            LOGGER.info("New contact added to the list " + message.content());
        } catch (ContactAlreadyExists e) {
            e.printStackTrace();
            LOGGER.error("Received a contact already in the contact list" + message.content());
        }
    }
}
