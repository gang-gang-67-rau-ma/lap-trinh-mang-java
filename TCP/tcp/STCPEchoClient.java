import java.net.*;
import java.io.*;

public class STCPEchoClient {
    public final static int defaultPort = 5070;

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",5070);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            for (int i = '0'; i <= '9'; i++) {
                os.write(i);
                int ch = is.read();
                System.out.println((char)ch);
            }
        } catch (IOException e) {
            System.err.println("socket Error:" + e);
        }
    }
}