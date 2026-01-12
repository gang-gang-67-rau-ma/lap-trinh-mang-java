import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client_fib {
    public static void main(String[] args) {
        try {
            // khoi tao bien
            Socket s = new Socket("localhost", 5000);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            int n = 0;
            Scanner sc = new Scanner(System.in);

            // nhap so tu ban phim
            System.out.print("Nhap so: ");
            n = sc.nextInt();

            // gui so den server
            os.write(n);

            int ch = is.read();
            System.out.println("Fib : " + ch);
        } catch (IOException e) {
            System.err.println("socket Error:" + e);
        }
    }
}
