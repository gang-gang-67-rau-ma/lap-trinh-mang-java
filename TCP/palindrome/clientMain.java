package palindrome;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

class clientMain {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            int n = 0;
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap chuoi: ");
            n = sc.nextLine();
            os.write(n);
            bool ch = is.read();
            System.out.print("Kqua : " + ch);
        } catch (IOException e) {
            System.err.println("socket Error:" + e);
        }
    }
}