package Test.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
public class MesPrinter {

    @Autowired
    private Message mes;

    public Message getMes() {
        return mes;
    }

    public void setMes(Message mes) {
        this.mes = mes;
    }

    public void Print(){
        System.out.println(mes.GetMessage());
    }

}
