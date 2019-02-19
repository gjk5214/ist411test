/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

/**
 *
 * @author gkemp
 */

import java.net.*;
import java.io.*;

public class Client extends ClientServer {

     protected Socket socket;
     private int newPort = 11000;
}

public Client(String url, int port) {
         try {
             socket = new Socket(url, port);
             newPort = Integer.parseInt(readFromSocket(socket));
             socket = new Socket (url, newPort);
             System.out.println("CLIENT: connected to " + url + ":" + port);
             newPort = newPort + 1;
             
         } catch (Exception e) {
             e.printStackTrace();
             System.exit(1);
         }
     } //Client()

public void run() {
         try {
             requestService(socket);
             socket.close();
             System.out.println("CLIENT: connection closed");
         } catch (IOException e) { 
             System.out.println(e.getMessage());
             e.printStackTrace(); 
         }
     } // run()

public static void main(String args[]) {
  
        EchoClient client = new EchoClient("localhost",10001);
        client.start();

    } // main()
