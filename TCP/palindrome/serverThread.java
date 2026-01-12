package palindrome;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class serverThread extends Thread {
    Socket s;

    serverThread(Socket s) {
        this.s = s;
    }

    public static String palindrome(String a) {
        String r = new StringBuilder(a).reverse().toString();

        if (a.equals(r)) {
            return "Chuỗi đối xứng";
        } else {
            return "Không phải chuỗi đối xứng";
        }
    }

    @Override
    public void run() {
        try {
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = is.read(buffer);
            String ch = new String(buffer, 0, len);
            String p = palindrome(ch);
            os.write(p.getBytes());
            os.flush();

            s.close();
        } catch (Exception e) {
            System.err.println("Socket Error: " + e.getMessage());
        }
    }

}