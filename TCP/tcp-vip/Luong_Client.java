package tcp-vip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Luong_Client extends Thread {
    // tao socket
    private Socket socket;

    // constructor
    public Luong_Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // tao luong nhan va gui
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // tao danh sach chuoi lay du lieu tu file
            List<String> data = doc_file.docFile("folder/input.txt");


            // doc tung dong va gui len server
            for (String line : data) {
                pw.println(line);
                System.out.println("Đã gửi: " + line);

                String response = br.readLine();
                System.out.println("Nhận từ server: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            socket.close();
        }
    }
}
