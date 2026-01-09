package threaded_protocols;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class clientUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        while (true) {
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[2048];

            BufferedReader inFormUser = new BufferedReader(new InputStreamReader(System.in));
            String sentence = inFormUser.readLine();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 2025);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("Nhan tu server: " + modifiedSentence);
            System.out.println("FROM SERVER:" + modifiedSentence);
            if (sentence.compareTo("quit") == 0)
                break;
        }
        clientSocket.close();
    }
}
