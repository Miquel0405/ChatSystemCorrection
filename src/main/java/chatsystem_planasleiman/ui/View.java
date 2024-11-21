package chatsystem_planasleiman.ui;

import chatsystem_planasleiman.contacts.Contact;
import chatsystem_planasleiman.contacts.ContactList;

public class View implements ContactList.Observer{
    
    
    public static void initialize() {
        System.out.println("Welcome to the chatsystem application");
        View view = new View();
        ContactList.getInstance().addObserver(view);
    }
    
    
    
    void displayContactList(){
        System.out.println();
        System.out.println("CONTACT LIST");
        for (Contact contact : ContactList.getInstance().getAllContacts()){
            System.out.println("  " + contact);
        }
        System.out.println();

        
    }

    @Override
    public void newContactAdded(Contact contact) {
        System.out.println("[VIEW] New contact: " + contact.getUsername());
        displayContactList();
    }

    @Override
    public void nicknameChanged(Contact newContact, String previousNickname) {
        System.out.println("[VIEW] Contact changed: " + newContact.getUsername() + "(was previously; " + previousNickname + ")" );
        displayContactList();
    }
}
