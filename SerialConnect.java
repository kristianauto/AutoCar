/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autonomcar;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krist
 */
public class SerialConnect
{

    private final String PORT = "COM5";
    private static SerialPort chosenPort;
    private static final int BAUD_RATE = 9600;
    private int DATABITS = 8;
    private int STOPBITS = 1; 
    private int PARITY_NONE = 0;  
    

    /**
     * Constructor, create object connection
     */
    public SerialConnect()
    {
        try
        {
            connect(PORT);
        } catch (Exception ex)
        {
            Logger.getLogger(SerialConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Connect the program to an Arduino using Serialcommunications and using
     * COM5 port
     *
     * @param portName COM port to be used to connect to Arduino
     * @throws Exception
     */
    void connect(String portName) throws Exception
    {

        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++)
        {

            if ((portNames[i].getSystemPortName().equals(portName)))
            {
                chosenPort = portNames[i];
                //     System.out.println( "what port" +chosenPort);
            }
        }
       chosenPort.setComPortParameters(BAUD_RATE, DATABITS, STOPBITS, PARITY_NONE);
        // Create input and outputstream from COM5 port 
        InputStream in = chosenPort.getInputStream();
        OutputStream out = chosenPort.getOutputStream();
      //  SerialPort addDataListener = chosenPort.addDataListener(new SerialPortDataListener);
        // Create reader object and Thread 
        SerialRead r = new SerialRead(in);
        Thread reader = new Thread(r);
        // Create writer object and Thread
        SerialWrite w = new SerialWrite(out);
        Thread writer = new Thread(w);

        // start the threads for reading and writing values to the arduino. 
        reader.start();
        writer.start();

    }
    
     
    
    
}
