package buoi4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server_ptb2 {
    public final static int defaultPort = 5000;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(defaultPort);
            System.out.println("Server dang lang nghe tai cong: " + defaultPort);
            while (true) {
                try {
                    Socket s = ss.accept();
                    ptb2 t = new ptb2(s);
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

class ptb2 extends Thread {
    Socket s;

    ptb2(Socket s) {
        this.s = s;
    }

    public static String giaiptb2(double a, double b, double c) {
        double delta = Math.pow(b, 2) - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Phương trình có hai nghiệm phân biệt:" + " x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Phương trình có nghiệm kép: x1 = x2 = " + x;
        } else {
            return "Phương trình vô nghiệm.";
        }
    }

    @Override
    public void run() {
        try {
            DataInputStream is = new DataInputStream(s.getInputStream());
            DataOutputStream os = new DataOutputStream(s.getOutputStream());
            String data = is.readUTF();

            String[] parts = data.split(" ");
            double a = Double.parseDouble(parts[0]);
            double b = Double.parseDouble(parts[1]);
            double c = Double.parseDouble(parts[2]);
            String res = giaiptb2(a, b, c);
            os.writeUTF(res);
            s.close();
        } catch (Exception e) {
            System.err.println("socket Error:" + e);
        }
    }
}
