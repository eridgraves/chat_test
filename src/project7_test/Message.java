package project7_test;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    public boolean isCommand;
    public String contents;
    public Client sender;
    public Date time;
    //public ChatRoom chatRoom;

    public Message(){
        contents = "DEFAULT CONSTRUCTOR CALL";
    }

    public Message(Client c, Date t, String s, boolean b){
        sender = c;
        time = t;
        contents = s;
        isCommand = b;
        System.out.println("MESSAGE CREATED");
    }

    public String toString(){
        //TODO ACKNOWLEDGE COMMANDS
        return (sender.toString() + " (" + time + "): " +  contents);
    }
}
