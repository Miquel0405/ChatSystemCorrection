package chatsystem_planasleiman.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UDPTests {
    private static final int test_port = 1871;
    @Test
    void sendReceiveTest()throws Exception{
        List<String> testMessages = Arrays.asList("alice", "bob", "chloe", "multi\nline String", "Éàç");


        List<String> receivedMessages = new ArrayList<>();
        UDPServer server = new UDPServer(test_port);
            server.addObserver(message -> {
                receivedMessages.add(message.content());
            });
        server.start();

        for (String msg : testMessages){
            UDPSender.sendLocalhost(test_port, msg);
        }
        

        Thread.sleep(100);
        System.out.println(receivedMessages);
        assertEquals(testMessages.size(), receivedMessages.size());
        assertEquals(testMessages,receivedMessages);
        

    }

}
