package chatsystem_planasleiman.network;

import java.net.InetAddress;


public class UDPMessage {
    public final String content;
    public final InetAddress origin;

    public UDPMessage(String content, InetAddress origin){
        this.content = content;
        this.origin = origin;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UDPMessage other = (UDPMessage) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UDPMessage [content=" + content + ", origin=" + origin + "]";
    }

    public String content() {
        return this.content;
    }

    public InetAddress origin() {
        return this.origin;
    }

}


    

