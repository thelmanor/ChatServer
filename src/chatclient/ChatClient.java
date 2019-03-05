
package chatclient;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClient extends Application  implements EventHandler<ActionEvent>, Runnable{

     TextArea msg=new TextArea();
    TextField text=new TextField();
    Button send=new Button("Send");
   
    Socket s1;
   // PrintStream ps;
    BufferedReader br;
    BufferedWriter bw;
    PrintWriter p;
         
    
    
    @Override
    public void start(Stage primaryStage)throws Exception {
                
        VBox vbox=new VBox();
        vbox.setSpacing(5);
        vbox.setMargin(msg, new Insets(20,20,20,20));
        vbox.setMargin(text, new Insets(20,20,20,20));
        vbox.setMargin(send, new Insets(20,20,20,20));
        
        ObservableList position=vbox.getChildren();
        position.addAll(msg,text,send);           
        send.setOnAction(this);
        Scene scene =new Scene(vbox);
        primaryStage.setTitle("Client Chat");       
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        ClientSocket();
        Thread t1=new Thread(this);
        t1.start();
    }
    
    public void ClientSocket() {
        String host="localhost";
        int port=1007;
     
        
        try{
        s1=new Socket(host,port);
        p=new PrintWriter(s1.getOutputStream());
        br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
         
         
        }
        catch(UnknownHostException e){
        System.out.println("Unknown host exception");
                }
        catch(IOException ie){
        System.out.println("IOException");
        
        }      
        
    }
     @Override
    public void run(){
        try{
            while(true){
            String t= br.readLine(); 
            msg.appendText("You: "+t+"\n");
            }
            
        }
        catch(Exception e){
        }
    
    
    }

    @Override
    public void handle(ActionEvent event) {
        
            if (event.getSource()==send){               
                System.out.println("Sending data...");
                   String d = text.getText();
                   text.clear();
                   msg.appendText("Me: "+d+"\n");
                   p.println(d);
                   p.flush();               
            }
    }    
   
    public static void main(String[] args) {
        launch(args);
    }
    
}

