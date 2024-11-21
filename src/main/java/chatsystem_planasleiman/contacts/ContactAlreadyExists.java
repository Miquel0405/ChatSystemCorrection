package chatsystem_planasleiman.contacts;

/*Error that is thrown when a contact is added twice to a list */
public class ContactAlreadyExists extends Exception{
    private String username;

    public ContactAlreadyExists(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "ContactAlreadyExists [username=" + username + "]";
    }

    

    
    
}
