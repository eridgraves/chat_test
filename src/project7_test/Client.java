package project7_test;


/* From Daniel Liang's book */

import java.io.*;
import java.net.*;
import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Client extends Application {
    // IO streams

    public String name;

    @Override // Override the start method in the Application class
    public void start(Stage ps) {


        // Login window

//        BorderPane loginPane = new BorderPane();
//        loginPane.setPadding(new Insets(5, 5, 5, 5));
//        loginPane.setStyle("-fx-border-color: green");
//        loginPane.setLeft(new Label("Enter your name: "));
//
//        TextField unField = new TextField();
//        unField.setAlignment(Pos.BOTTOM_RIGHT);
//        loginPane.setCenter(unField);
//
//        Stage loginStage = new Stage();
//        Scene loginScene = new Scene(loginPane, 500, 300);
//        loginStage.setTitle("Log In");
//        loginStage.setScene(loginScene);
//        loginStage.show();


        // Panel p to hold the label and text field
        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5, 5, 5, 5));
        paneForTextField.setStyle("-fx-border-color: green");
        paneForTextField.setLeft(new Label("Enter a message: "));

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField.setCenter(tf);

        BorderPane mainPane = new BorderPane();
        // Text area to display contents
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(paneForTextField);

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 450, 200);
        ps.setTitle("Client"); // Set the stage title
        ps.setScene(scene); // Place the scene in the stage
        ps.show(); // Display the stage
        tf.setOnAction(e -> {
                    try {
                        // Create a socket to connect to the server
                        @SuppressWarnings("resource")
                        Socket socket = new Socket("localhost", 4242);
                        ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
                        // Create an input stream to receive data from the server
                        Message mes = new Message(new Date(), tf.getText(), false);
                        toServer.writeObject(mes);
                        // Create an output stream to send data to the server
                        tf.setText("");
                    } catch (IOException ex) {
                        ta.appendText(ex.toString() + '\n');
                    }
                });

        // Set Client Username
        //this.name = tf.getText();

        System.out.println("Name set: " + this.name);

        //paneForTextField.setLeft(new Label("Enter a message: "));

//        tf.setOnAction(e -> {
//            try {
//                // Get the message from the text field
//                String mesText = tf.getText().trim();
//
//                // Make a message object to send to the server
//                Message mes = new Message(this, new Date(), mesText, false);
//
//                // Send the radius to the server
//                toServer.writeObject(mes);
//                //toServer.flush();
//
//                // Get response from the server
//                Object received = fromServer.readObject();
//
//                // Display to the text area
//                ta.appendText("Server says: " + received.toString() + "\n");
//
//            }
//            catch (IOException | ClassNotFoundException ex) {
//                System.err.println(ex);
//            }
//        });


    }

    public static void main(String[] args) {
        launch(args);
    }
}
