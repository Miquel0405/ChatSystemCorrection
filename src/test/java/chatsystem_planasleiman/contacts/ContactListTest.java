package chatsystem_planasleiman.contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContactListTest {

    interface FallibleCode{
        void run() throws Exception;
    }


    private static void assertThrows(FallibleCode code){
        try {
            code.run();
            throw new RuntimeException("Code should have thrown an exception");
        } catch (Exception e) {
            // OK, we expected an exception
        }
    }

    @BeforeEach
    public void clearContactList(){
        ContactList.getInstance().clear();
    }
    
    @Test
    void contactAdditionTest() throws ContactAlreadyExists{
        ContactList contacts = ContactList.getInstance();
        

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
        ContactList contacts = ContactList.getInstance();
        

        contacts.addUser("alice");
        assert contacts.hasUserName("alice");
        assertThrows(() -> contacts.addUser("alice"));
        assertThrows(() -> contacts.addUser("alice"));
        


    }
}
