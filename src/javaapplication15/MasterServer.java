/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.net.*;
import java.io.*;
/**
 *
 * @author gkemp
 */


public class MasterServer extends ClientServer {
    
    private int clientPortNumber;
    private ServerSocket port;
    private Socket socket;
    //private DatabaseServer ThreadedServer[];
    private int numberServer;
    private String stNum;
    
        
    public MasterServer(int portNum, int nBacklog)  {
        
        clientPortNumber = socket.getPort();
        
        try {
            port = new ServerSocket (portNum, nBacklog);
        } catch (IOException e) {
            e.printStackTrace();
        }     
    } 
 
    
    public void run() {
        try {
            System.out.println("Server at " 
                               + InetAddress.getLocalHost() + " waiting for connections ");
            while(true) {
                socket = port.accept();
                System.out.println("Accepted a connection from " + socket.getInetAddress() 
                        + " Port: " + clientPortNumber);
                provideService(socket);
                numberServer++;
                
                stNum = Integer.toString(numberServer);
                writeToSocket(socket, stNum);
                
                int i = 0;
                //ThreadedServer[i] = new Server(numberServer, 5);
                //for (i = 0; i < ThreadedServer.length; i++) {
                //ThreadedServer[i].start();
               // i++;
               // }
                  
                socket.close();
                System.out.println("Closed the connection\n");
            }
        } catch (IOException e) {
             e.printStackTrace();
        }
    } // run()

    /**
     *  provideService() defines this server's service, which consists
     *   of simply echoing whatever string it receives from the client.
     *  The server's protocol calls for it to begin by saying hello
     *  and end by saying goodbye. Isn't it polite?
     */
    protected void provideService (Socket socket) {
        String str="";
        try {
            writeToSocket(socket, "Hello, how may I help you?\n");
            do {     
                str = readFromSocket(socket);
                if (str.toLowerCase().equals("goodbye"))
                    writeToSocket(socket, "Goodbye\n");
                else
                    writeToSocket( socket, "You said '" + str + "'\n");
            }  while (!str.toLowerCase().equals("goodbye"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // provideServer()

     
    public static void main(String args[]) {
        DatabaseServer server = new Server(10001,5);
        server.start();
    } // main()
} // EchoServer
