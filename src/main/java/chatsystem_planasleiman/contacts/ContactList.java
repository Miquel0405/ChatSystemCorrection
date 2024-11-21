package chatsystem_planasleiman.contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactList {

    public interface Observer{
        void newContactAdded(Contact contact);
        void nicknameChanged(Contact newContact, String previousNickname);
    }
    
    
    
    List<Contact> contacts = new ArrayList<>();

    List<Observer> observers = new ArrayList<>();
    
    private static ContactList INSTANCE = new ContactList();

    public static ContactList getInstance(){
        return INSTANCE;
    }

    private ContactList(){
    }

    public synchronized void addObserver(Observer obs){
        this.observers.add(obs);
    }



    public synchronized void addUser(String username) throws ContactAlreadyExists{
        if (hasUserName(username)){
            throw new ContactAlreadyExists(username);
        }else {
            Contact contact = new Contact(username);
            contacts.add(contact);
            for (Observer obs : observers){
                obs.newContactAdded(contact);
            }
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

    public synchronized List<Contact> getAllContacts(){
        // return defensive copy of the contacts to avoid anybody modifying it or doing unsynchronized access
        return new ArrayList<>(this.contacts);
    }

    public synchronized void clear(){
        this.contacts.clear();
    }

}