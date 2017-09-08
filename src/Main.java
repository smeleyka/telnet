import org.crsh.telnet.TelnetPlugin;
import sun.net.TelnetInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by smeleyka on 07.09.17.
 */
public class Main {
    public static void main(String[] args) throws Exception{



        Socket socket = new Socket("10.10.0.22", 23);
        socket.setKeepAlive(true);
        InputStreamReader inputStream  = new InputStreamReader(socket.getInputStream());
        TelnetInputStream tis = new TelnetInputStream(socket.getInputStream(),false);
        BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter w = new PrintWriter(socket.getOutputStream(),true);



        int i =0;
        while ((i=tis.read()) != -1) {
            System.out.print((char)i);
             };
            //System.out.println("1");

//        w.print("1234\r\n");
//        System.out.println("2");
//        // also tried simply \n or \r
////w.flush();
////Thread.sleep(1000);
//
//        while ((c = r.read()) != -1)
//            System.out.print((char)c);
//        System.out.println("3");
//
//        w.print("1234\r\n");
////Thread.sleep(1000);
//        while ((c = r.read()) != -1)
//            System.out.print((char)c);
//        System.out.println("4");

        socket.close();
    }
}
