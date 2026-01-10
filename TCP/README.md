# Cấu trúc thư mục chuẩn cho bài TCP

- ClientThread.java
- ServerThread.java
- ClientMain.java
- ServerMain.java
- Các lớp xủ lí khác nếu cần ()

# 1. ClientThread

```java
public class ClientThread extends Thread {
    // tao socket
    private Socket socket;

    // constructor
    public Luong_Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // logic lam gi do
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

# 2. ServerThread

```java
public class ServerThread extends Thread {
    // tao socket
    private Socket socket;

    // constructor
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // logic lam gi do
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hàm làm gì đó (tính toán , xử lí chuổi ...)
    public static string foo(int n) {
        return "foo";
    }
}
```

# 3. ClientMain.java

```java
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientTCP {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    // Hàm khởi tạo kết nối
    public void startClient(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            this.out = new DataOutputStream(socket.getOutputStream());

            // Luồng nhận dữ liệu từ Server
            new Thread(() -> { listenServer(); }).start();

            // Luồng gửi dữ liệu lên Server
            sendRequest();

        } catch (Exception e) {}
    }

    // Hàm đóng kết nối
    public void closeClient() {
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (Exception e) {}
    }

    // Hàm lắng nghe dữ liệu từ Server
    private void listenServer() {
        try {
            while (true) {
                String response = in.readUTF();
                processServerResponse(response);
            }
        } catch (Exception e) {
            closeClient();
        }
    }

    // Hàm xử lý gửi yêu cầu
    private void sendRequest() {
        try {
            while (true) {
                String message = getInput();
                out.writeUTF(message);
                out.flush();
            }
        } catch (Exception e) {
            closeClient();
        }
    }

    // Hàm lấy input (từ bàn phím)
    private String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    // Hàm xử lý dữ liệu nhận được từ Server (hiển thị UI, xử lý logic...)
    private void processServerResponse(String data) {
        // Logic xử lý phản hồi từ Server
    }

    // Main chạy client
    public static void main(String[] args) {
        new ClientTCP().startClient("localhost", 3636);
    }
}
```

# 4. ServerMain.java

```java
import java.net.*;
import java.io.*;

public class ServerTCP {

    private ServerSocket ss;
    private int port;

    // Hàm khởi tạo seversocket + lắng nghe client
    public void startServer(int port) {
        this.port = port;
        try {
            this.ss = new ServerSocket(this.port);
            new Thread(() -> { listenClient(); }).start();
        } catch (Exception e) {}
    }

    // Hàm đóng server
    public void closeServer() {
        try {
            if (this.ss != null) {
                this.ss.close();
            }
        } catch (Exception e) {}
    }

    // Hàm lắng nghe client socket
    private void listenClient() {
        while (!this.ss.isClosed()) {
            try {
                Socket s = ss.accept();
                new Thread(() -> handleClient(s)).start();
            } catch (Exception e) {}
        }
    }

    // Hàm xử lý I/O cho Client
    private void handleClient(Socket s) {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream())); 
            DataOutputStream output = new DataOutputStream(s.getOutputStream()); ) {
            while (true) {
                String message = in.readUTF();
                String data = processClientRequest(message);
                output.writeUTF(data);
                output.flush();
            }
        } catch (Exception e) {}
    }

    // Hàm xử lý dữ liệu Client gửi lên, tính PTB2, ...
    private String processClientRequest(String data) {
        // Xử lý yêu cầu của người dùng
        return processedData;
    }

    // Main chạy server
    public static void main(String[] args) {
        new ServerTCP().startServer(3636);
    }
}
```
