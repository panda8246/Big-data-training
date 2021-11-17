package Test.Spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Message {

    @Value("假面骑士Build")
    private String mes;

    public String GetMessage(){
        return mes;
    }
}
