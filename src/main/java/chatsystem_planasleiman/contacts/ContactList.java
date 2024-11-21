package chatsystem_planasleiman.contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactList {

    List<Contact> contacts = new ArrayList<>();
    

    public ContactList(){
    }

    public synchronized void addUser(String username) throws ContactAlreadyExists{
        if (hasUserName(username)){
            throw new ContactAlreadyExists(username);
        }else {
            Contact contact = new Contact(username);
            contacts.add(contact);
        }
    }

    public synchronized boolean hasUserName(String username){
        for (Contact contact : contacts){
            if (contact.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
}