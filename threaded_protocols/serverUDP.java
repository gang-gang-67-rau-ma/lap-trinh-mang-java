package threaded_protocols;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class serverUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(2025);
        System.out.println("Server dang lang nghe tai cong: 2025");
        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            new ClientHandler(serverSocket, receivePacket).start();
        }
    }

    static class ClientHandler extends Thread {
        private DatagramSocket socket;
        private DatagramPacket packet;

        public ClientHandler(DatagramSocket socket, DatagramPacket packet) {
            this.socket = socket;
            this.packet = packet;
        }

        @Override
        public void run() {
            try {
                String sentence = new String(packet.getData(), 0, packet.getLength());
                InetAddress IPAddress = packet.getAddress();
                int port = packet.getPort();
                String sentence_to_client = sentence + " (server accepted!)";
                byte[] sendData = sentence_to_client.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                socket.send(sendPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
