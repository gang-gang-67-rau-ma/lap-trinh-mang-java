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
    public ClientThread(Socket socket) {
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            socket.close();
        }
    }
}
```

# 2. ServerThread

```java
public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // nhan du lieu tu client
            String data;

            while ((data = br.readLine()) != null) {
                System.out.println("Nhận được: " + data);
                try {
                    String result = giaiptb2(1,2,3);
                    String q = "Phương trình: " + a + "x^2 + " + b + "x + " + c + " = 0";
                    String output = q + " => " + result;
                    pw.println(output);
                    ghi_file.ghiFile(output + "\n", "folder/output.txt");
                } catch (NumberFormatException e) {
                    pw.println("Dữ liệu không hợp lệ: " + data);
                }
            }
        } 
        catch (Exception e) {
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
public class ClientTCP {
    public static void main(String[] args) {
        try {
            // khoi tao client noi den server
            Socket socket = new Socket("localhost", 9999);
            System.out.println("Da ket noi den server");

            // khoi tao luong client de bat dau cong viec
            ClientThread lc = new ClientThread(socket);
            lc.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

# 4. ServerMain.java

```java
public class ServerTCP {
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
                ServerThread sv = new ServerThread (socket);
                ls.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

# BT
1. CHUỖI ĐỐI XỨNG
### Viết chương trình thiết lập và kết nối giữa Client và Server TCP và thực hiện trong luồng xử lý "Chuỗi đối xứng".

## **[Viết chương trình thiết lập và kết nối giữa Client và Server TCP và thực hiện trong luồng xử lý số fibonaci.](https://github.com/gang-gang-67-rau-ma/lap-trinh-mang-java/tree/main/TCP/tcp/fibonaci)**