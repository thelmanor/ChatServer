
package chatserver;

import java.io.*;
import java.net.ServerSocket;
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

/**
 *
 * @author tnora
 */
public class ChatServer extends Application  implements EventHandler<ActionEvent>, Runnable {
    TextArea msg=new TextArea();
    TextField text=new TextField();
    Button send=new Button("Send");
   
    Socket s1;
    ServerSocket ss;
    BufferedReader br;
    //BufferedWriter bw;    
    PrintWriter p;
    
    
    
    @Override
    public void start(Stage primaryStage)throws Exception {
           
        VBox vbox=new VBox();
        vbox.setSpacing(5);
        VBox.setMargin(msg, new Insets(20,20,20,20));
        VBox.setMargin(text, new Insets(20,20,20,20));
        VBox.setMargin(send, new Insets(20,20,20,20));
        
            
        ObservableList position=vbox.getChildren();
        position.addAll(msg,text,send);
        send.setOnAction(this);
        
        Scene scene =new Scene(vbox);
        primaryStage.setTitle("Server Chat");       
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        ServerSocket();
        Thread t1=new Thread(this);
        t1.start();
    }
    
   public void ServerSocket(){
      System.out.println ("Executing Server...");
		try{
			
                    ss= new ServerSocket (1007);
                    s1=ss.accept();
                    br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                    p=new PrintWriter(s1.getOutputStream());

		}
		catch(Exception e) {
                    System.out.println("Error in socketSetup()");
                    System.out.println(e);
		}
    }
     
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
    public static void main (String[] args){
       launch(args);
    }
           
           
}