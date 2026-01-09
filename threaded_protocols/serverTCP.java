package threaded_protocols;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serverTCP {
    public final static int defaultPort = 5070;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(defaultPort);
            System.out.print("Server dang lang nghe tai cong: " + defaultPort);
            while (true) {
                try {
                    Socket s = ss.accept();
                    thread_serverTCP t = new thread_serverTCP(s);
                    t.start();
                } catch (IOException e) {
                    System.err.println("Connection Error: " + e);
                }
            }
        } catch (IOException e) {
            System.err.println(" Server Creation Error:" + e);
        }
    }

    static class thread_serverTCP extends Thread {
        Socket s;

        thread_serverTCP(Socket s) {
            this.s = s;
        }

        @Override
        public void run() {
            try {
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                int ch = 0;
                while (true) {
                    ch = is.read();
                    if (ch == -1)
                        break;
                    os.write(ch);
                }
                s.close();
            } catch (Exception e) {
                System.err.println("socket Error:" + e);
            }
        }
    }
}
