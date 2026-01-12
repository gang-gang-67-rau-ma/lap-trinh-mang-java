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

            System.out.print("Nhap chuoi: ");
            String n = sc.nextLine();
            os.write(n.getBytes());
            os.flush();
            int ch = is.read();
            System.out.println("Kqua: " + (char) ch);

            sc.close();
            s.close();
        } catch (IOException e) {
            System.err.println("Socket Error: " + e.getMessage());
        }
    }
}
