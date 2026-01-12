import java.io.InputStream;
import java.io.OutputStream;

class serverThread extends Thread {
    Socket s;

    serverThread(Socket s) {
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