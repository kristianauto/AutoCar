/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonomcar;

import java.io.PrintWriter;

import com.fazecast.jSerialComm.*;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Krist
 */
public class SerialWrite implements Runnable
{

    private String switchCaseNr;
    private OutputStream outStream;

    public SerialWrite(OutputStream outStream)
    {
         this.outStream= outStream; 
         
    }

    /**
     * set the command for the arduino
     *
     * @param switchCaseNr
     */
    public void setCommand(String switchCaseNr)
    {
        this.switchCaseNr = switchCaseNr;
    }

    /**
     * Default method which is run when thread is created
     */
    @Override
    public void run()
    {

       

    }

    /**
     * Write commands for the car to arduino
     */
    public synchronized void writeToArduino()
    {
        try
        {
            Thread.sleep(100);
            PrintWriter output = new PrintWriter(outStream);
            output.write(switchCaseNr); // which switch case to be entered  
            output.flush();
            System.out.println("Thread:  ");
        } catch (Exception e)
        {
        }

    }
}
