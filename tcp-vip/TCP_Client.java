package dau_2251220146;

import java.net.Socket;

public class TCP_Client {
    public static void main(String[] args) {
        try {
            // khoi tao client noi den server
            Socket socket = new Socket("localhost", 9999);
            System.out.println("Da ket noi den server");

            // khoi tao luong client de bat dau cong viec
            Luong_Client lc = new Luong_Client(socket);
            lc.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
