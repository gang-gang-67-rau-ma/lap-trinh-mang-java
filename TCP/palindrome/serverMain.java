package palindrome;

public class serverMain {
    public final static int defaultPort = 5000;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(defaultPort);
            System.out.println("Server dang lang nghe tai cong: " + defaultPort);
            while (true) {
                try {
                    Socket s = ss.accept();
                    serverThread t = new serverThread(s);
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