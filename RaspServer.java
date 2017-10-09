/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonomcar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Krist
 */
public class RaspServer extends Thread
{
      //sets the port to connect to server to 5000
    private final static int port = 5000;
    private Processor pro;
    
    
    
    /**
     * starts the server and creates new threads of cliendhandler based on incoming connections
     *
     * @param port port client will be using to connect to the server with
     * @throws IOException
     */
    public void run(int port) throws IOException
    {
        //creates a listeningsocket based on the default port when client connects to server
        ServerSocket welcomeSocket = new ServerSocket(port);
        while (true)
        {
            // Create a clientSocket based on the listeningsocket acceptance
            Socket connectionS = welcomeSocket.accept();
            
            System.out.println("incoming connection ");
            //try to create a client session/new thread
            try
            {   
                //creates a new instance of clienthadler with socket and register as param to be used 
                this.pro = new Processor(connectionS);
                //create a new instance of thread with clienthandler as paramter
                // And starts the thread 
                new Thread(pro).start();
                System.out.println("new thread was created");

            }
            //if try fails catch the error
            catch (Exception e)
            {
                System.out.println("could not create thread");
            }

        }
    }
    
    
}
