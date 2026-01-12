package palindrome;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);

            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(System.in);
            byte[] buffer = new byte[1024];

            System.out.print("Nhap chuoi: ");
            String n = sc.nextLine();

            os.write(n.getBytes());
            os.flush();
            int len = is.read(buffer);
            String kq = new String(buffer, 0, len);
            System.out.println("Kqua: " + kq);

            sc.close();
            s.close();
        } catch (IOException e) {
            System.err.println("Socket Error: " + e.getMessage());
        }
    }
}
