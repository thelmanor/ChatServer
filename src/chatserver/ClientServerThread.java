/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.*;
import java.net.Socket;
import static javafx.application.Application.launch;

/**
 *
 * @author tnora
 */
public class ClientServerThread implements Runnable {
   
    Socket sc=null;
    
    public ClientServerThread(Socket sc){
        this.sc=sc;

    }
    public void run(){
       try{
       BufferedReader b=new BufferedReader(new InputStreamReader(sc.getInputStream()));
       PrintWriter p=new PrintWriter(sc.getOutputStream(),true);
       String s;
       s=b.readLine();
       System.out.println("Client String="+ s);
       p.println("msg received...");
       }
       catch(IOException e){
       e.printStackTrace();       
       }
       
    
    }
     public static void main(String[] args) {
        launch(args);
    }
   
}
