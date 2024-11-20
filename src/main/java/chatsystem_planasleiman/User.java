package chatsystem_planasleiman;

import java.util.Scanner;

public class User {
    private ContactList contList; 
    private String username;

    /*Constructors */
    public User() {
        this.contList = new ContactList();
    }

    public User(String username) {
        this.contList = new ContactList();
        this.username = username;
    }

    /*Getters */
    public ContactList getContList() {
        return contList;
    }

    public String getUsername() {
        return username;
    }
    
    


    /*Asks the user to choose a username which is not in the contact list  */
    public void SetUsername() {
		String userInput=null;
		Scanner scanner = new Scanner(System.in);
		
        // Prompt the user to enter a string
        System.out.print("Please enter a Username: ");

        // Read the user's input as a string
        userInput = scanner.nextLine();

        // Display the entered string
        System.out.println("You entered: " + userInput);
        
        //Checks if the input is in the contact list and if it is then asks for another username
        while (this.getContList().exists(userInput)){
        	System.out.print("Username already exists,"
        			+ "Please enter a new Username: ");
        	userInput = scanner.nextLine();
        }

        // Display the entered string
        System.out.println("Your username is: " + userInput);

        // Close the scanner to free up resources
        scanner.close();
        this.username = userInput;
        
	}
    

}
