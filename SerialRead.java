package autonomcar;

import com.fazecast.jSerialComm.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author Krist
 */
public class SerialRead implements Runnable
{

    private InputStream inStream;

    /**
     * Sernd inputstream from connection to reader
     *
     * @param inStream input from Connection to Arduino
     */
    public SerialRead(InputStream inStream)
    {
        this.inStream = inStream;
    }

    /**
     * Default method which is run when thread is created
     */
    @Override
    public void run()
    {

        while (true)
        {
            readFromArduino();
        }

    }

    /**
     * Read from commands for the car to arduino
     */
    public void readFromArduino()
    {
        try
        {
            Thread.sleep(100);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

            System.out.println("Thread:  ");
        } catch (Exception e)
        {
        }

    }
}
