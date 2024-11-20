package chatsystem_planasleiman;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;


public class ContactList {
	private HashMap<String, InetAddress> contactlist;
	
	public ContactList() {
		this.contactlist = new HashMap<>();
	}
	
	
	//Add a new contact to the contact list
	public void addContact(String cont, InetAddress addr) {
		this.contactlist.put(cont, addr);
	}
	
	//Remove a contact from the contact list
	public void deleteContact(String cont) {
		this.contactlist.remove(cont);
	}
	
	//Checks if a user exists in a contact list
	public Boolean exists(String username) {
		return (this.contactlist.containsKey(username));
	}

    // Get the IP address of a specific contact
    public InetAddress getContactAddress(String name) {
        return contactlist.get(name);
    }
    
	// Get the entire contact list
    public Map<String, InetAddress> getContacts() {
        return contactlist;
    }

	//Print the contact list
	public void printContactList() {
		this.contactlist.forEach((key, value) -> System.out.println("username: " + key + ", IP address: " + value));
        
	}

	
}
