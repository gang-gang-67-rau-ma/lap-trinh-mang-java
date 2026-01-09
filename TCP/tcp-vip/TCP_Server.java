package dau_2251220146;

import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {
    public static void main(String[] args) {
        try {
            // khoi tao server o cong 9999
            ServerSocket server = new ServerSocket(9999);
            System.out.println("Server da san sang");

            // lang nghe client
            while (true) {
                Socket socket = server.accept();
                System.out.println("Co ket noi moi: " + socket.getInetAddress().getHostAddress());
                // khoi tao luong server de xu li
                Luong_Server ls = new Luong_Server(socket);
                ls.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
