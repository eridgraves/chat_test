package project7_test;

/* From Daniel Liang's book */


import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Server {
    private ObjectOutputStream outputToFile;
    private ObjectInputStream inputFromClient;
    public static void main(String[] args) {
        new Server();
    }
    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            System.out.println("server started");
            outputToFile = new ObjectOutputStream(new FileOutputStream("student.dat", true));
            while (true){
                Socket socket = serverSocket.accept();
                inputFromClient = new ObjectInputStream(socket.getInputStream());
                Object obj = inputFromClient.readObject();
                Message thing = (Message)obj;
                outputToFile.writeObject(thing + "\n");
                System.out.println("blahhhhh");
            }
        } catch (ClassNotFoundException | IOException e) {
        }
        finally {
            try {
                inputFromClient.close();
                outputToFile.close();
            } catch (Exception e) {

            }
        }

    }
}

