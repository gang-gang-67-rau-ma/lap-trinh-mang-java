package threaded_protocols;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class clientTCP {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5070);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            for (int i = '0'; i <= '9'; i++) {
                os.write(i);
                int ch = is.read();
                System.out.println((char) ch);
            }
        } catch (IOException e) {
            System.err.println("socket Error:" + e);
        }
    }
}
