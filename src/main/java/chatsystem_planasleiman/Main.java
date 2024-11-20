package chatsystem_planasleiman;

import java.net.InetAddress;

public class Main {
    /*The aplication creates a new user and connects it to the network */
    public static void main(String[] args) {
        try{
            User user = new User();
            ThreadManager manager = new ThreadManager(user, InetAddress.getByName("localhost"));
            manager.startThreads();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
