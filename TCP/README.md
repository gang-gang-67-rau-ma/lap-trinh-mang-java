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

                // tach chuoi
                String[] arr = data.trim().split(";");
                if (arr.length != 3) {
                    pw.println("Dữ liệu không hợp lệ: " + data);
                    continue;
                }

                try {
                    double a = Double.parseDouble(arr[0]);
                    double b = Double.parseDouble(arr[1]);
                    double c = Double.parseDouble(arr[2]);
                    String result = giaiptb2(a, b, c);
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

# BT (code full từ đầu đến đít theo cấu trúc ở trên nhá)

# 1 – XỬ LÝ CHUỖI (CƠ BẢN)
### 📌 Yêu cầu

* Client đọc từng dòng từ file `input.txt`
* Gửi từng dòng lên Server
* Server xử lý và trả về:

  * Chuỗi in hoa
  * Số ký tự của chuỗi
* Client in kết quả ra màn hình

### 📥 Ví dụ

**Client gửi**

```
lap trinh mang
```

**Server trả**

```
IN HOA: LAP TRINH MANG | SO KY TU: 15
```

📌 Gợi ý xử lý:

```java
data.toUpperCase();
data.length();
```

# 2 – TÍNH TOÁN SỐ HỌC

### 📌 Yêu cầu

* Client gửi 2 số nguyên theo định dạng: `a;b`
* Server trả về:

  * Tổng
  * Hiệu
  * Tích
  * Thương (nếu b ≠ 0)

### 📥 Ví dụ

**Client gửi**

```
10;5
```

**Server trả**

```
Tong=15, Hieu=5, Tich=50, Thuong=2
```

📌 Gợi ý:

```java
String[] arr = data.split(";");
int a = Integer.parseInt(arr[0]);
```

# 3 – KIỂM TRA SỐ NGUYÊN TỐ

### 📌 Yêu cầu

* Client gửi một số nguyên `n`
* Server kiểm tra:

  * Có phải số nguyên tố không
* Trả kết quả về Client

### 📥 Ví dụ

**Client gửi**

```
17
```

**Server trả**

```
17 la so nguyen to
```

📌 Gợi ý:

```java
for (int i = 2; i <= Math.sqrt(n); i++)
```

# 4 – XỬ LÝ MẢNG SỐ

### 📌 Yêu cầu

* Client gửi một dãy số nguyên:

```
1,3,5,2,8,4
```

* Server:

  * Tính tổng
  * Tìm số lớn nhất
  * Sắp xếp tăng dần
* Trả kết quả về Client

### 📥 Ví dụ

**Server trả**

```
Tong=23 | Max=8 | Mang sap xep: 1 2 3 4 5 8
```

📌 Gợi ý:

```java
String[] arr = data.split(",");
Arrays.sort(arr);
```

# 5 – FILE + TCP + XỬ LÝ

### 📌 Yêu cầu

* Client đọc file `ptb2.txt`

```
1;2;1
1;5;6
```

* Gửi từng dòng lên Server
* Server giải phương trình bậc 2
* Ghi kết quả vào file `ketqua.txt`
* Đồng thời gửi kết quả về Client

### 📥 Ví dụ kết quả

```
PT: 1x^2 + 2x + 1 = 0 => x = -1
PT: 1x^2 + 5x + 6 = 0 => x1 = -2, x2 = -3
```

📌 Gợi ý:

* Dùng `BufferedReader`
* Ghi file bằng `FileWriter`