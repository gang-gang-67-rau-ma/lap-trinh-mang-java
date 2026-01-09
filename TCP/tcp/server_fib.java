package buoi4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server_fib {
    public final static int defaultPort = 5000;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(defaultPort);
            System.out.println("Server dang lang nghe tai cong: " + defaultPort);
            while (true) {
                try {
                    Socket s = ss.accept();
                    fib t = new fib(s);
                    t.start();
                } catch (IOException e) {
                    System.err.println("Connection Error: " + e);
                }
            }
        } catch (IOException e) {
            System.err.println(" Server Creation Error:" + e);
        }
    }
}

class fib extends Thread {
    Socket s;

    fib(Socket s) {
        this.s = s;
    }

    public static int fibonacci(int n) {
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Override
    public void run() {
        try {
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            int ch = is.read();
            int fibo = fibonacci(ch);
            os.write(fibo);
            s.close();
        } catch (Exception e) {
            System.err.println("socket Error:" + e);
        }
    }
}
