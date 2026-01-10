package tcp-vip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Luong_Server extends Thread {
    // tao socket
    private Socket socket;

    // constructor
    public Luong_Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // tao luong nhan va gui
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // nhan du lieu tu client
            String data;

            while ((data = br.readLine()) != null) {
                System.out.println("Nhận được: " + data);

                // tach chuoi
                String[] arr = data.trim().split(";");
                if (arr.length != 3) {
                    pw.println("Dữ liệu không hợp lệ: " + data);
                    continue;
                }

                // giai phuong trinh
                try {
                    double a = Double.parseDouble(arr[0]);
                    double b = Double.parseDouble(arr[1]);
                    double c = Double.parseDouble(arr[2]);

                    // dinh dang chuoi de ghi vao file va hien thi trong terminal
                    String result = giaiptb2(a, b, c);
                    String q = "Phương trình: " + a + "x^2 + " + b + "x + " + c + " = 0";
                    String output = q + " => " + result;
                    pw.println(output);
                    ghi_file.ghiFile(output + "\n", "folder/output.txt");
                } catch (NumberFormatException e) {
                    pw.println("Dữ liệu không hợp lệ: " + data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            socket.close();
        }
    }

    // giai phuong trinh bac 2
    public static String giaiptb2(double a, double b, double c) {
        if (a == 0) {
            if (b == 0) {
                return (c == 0) ? "Phương trình vô số nghiệm." : "Phương trình vô nghiệm.";
            } else {
                double x = -c / b;
                return "Phương trình bậc nhất, nghiệm: x = " + x;
            }
        }

        double delta = Math.pow(b, 2) - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Phương trình có hai nghiệm phân biệt: x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Phương trình có nghiệm kép: x1 = x2 = " + x;
        } else {
            return "Phương trình vô nghiệm.";
        }
    }
}
