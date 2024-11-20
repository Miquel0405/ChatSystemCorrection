package chatsystem_planasleiman;

import java.net.InetAddress;

//Class message containing a String (the message to be sent) and an address to send to. 
public class Message {
    private String content;
    private InetAddress recipientAddress;
    public Message(String content, InetAddress recipientAddress) {
        this.content = content;
        this.recipientAddress = recipientAddress; 
    }
    public String getContent() { 
        return content; 
    }
    public InetAddress getRecipientAddress() { 
        return recipientAddress; 
    }
}
