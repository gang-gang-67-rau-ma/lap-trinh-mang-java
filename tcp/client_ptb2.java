package buoi4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class client_ptb2 {
    public static void main(String[] args) {
        try {
            // khoi tao bien
            Socket s = new Socket("localhost", 5000);
            DataInputStream is = new DataInputStream(s.getInputStream());
            DataOutputStream os = new DataOutputStream(s.getOutputStream());

            // nhap so tu ban phim
            Scanner scanner = new Scanner(System.in);

            double a = nhapHeSo(scanner, "a");
            double b = nhapHeSo(scanner, "b");
            double c = nhapHeSo(scanner, "c");

            String data = a + " " + b + " " + c;
            System.out.println("Gửi dữ liệu đến server: " + data);

            if (a == 0) {
                if (b == 0) {
                    if (c == 0) {
                        System.out.println("Phương trình có vô số nghiệm.");
                        s.close();
                    } else {
                        System.out.println("Phương trình vô nghiệm.");
                        s.close();
                    }
                } else {
                    double x = -c / b;
                    System.out.println("Phương trình có một nghiệm: x = " + x);
                }
            } else {
                // gui so den server
                os.writeUTF(data);
            }

            // nhan ket qua tu server
            String result = is.readUTF();
            System.out.println("Kết quả từ server: " + result);
            s.close();
        } catch (IOException e) {
            System.err.println("socket Error:" + e);
        }
    }

    public static double nhapHeSo(Scanner scanner, String tenHeSo) {
        while (true) {
            try {
                System.out.print("Nhập hệ số " + tenHeSo + ": ");
                double heSo = scanner.nextDouble();
                return heSo;
            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Vui lòng chỉ nhập số. Hãy thử lại.");
                scanner.next();
            }
        }
    }
}
