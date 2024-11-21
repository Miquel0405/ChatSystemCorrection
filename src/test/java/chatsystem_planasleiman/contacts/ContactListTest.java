package chatsystem_planasleiman.contacts;

import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContactListTest {
    
    @Test
    void contactAdditionTest() throws ContactAlreadyExists{
        ContactList contacts = new ContactList();

        assert !contacts.hasUserName("alice");
        contacts.addUser("alice");
        assert contacts.hasUserName("alice");
        assert !contacts.hasUserName("bob");

        assert !contacts.hasUserName("bob");
        contacts.addUser("bob");
        assert contacts.hasUserName("bob");
        assert contacts.hasUserName("alice");

    }

    @Test
    void contactDuplicationTest() throws ContactAlreadyExists{
        ContactList contacts = new ContactList();
        contacts.addUser("alice");
        assert contacts.hasUserName("alice");
        try {
            contacts.addUser("alice");
            throw new RuntimeException("Expected ContactAlreadyExist Exeption");
        } catch (Exception e) {
            //Expected outcome
        }
        


    }
}
