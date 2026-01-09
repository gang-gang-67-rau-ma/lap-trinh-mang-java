# Cấu trúc thư mục chuẩn cho bài RMI

- ClientMain.java  
- [Interface để làm gì đó (tính ptb2/fib)].java  
- Remote[Tên Interface]Server.java  
- ServerMain.java  

# [Interface Name].java

```java
public interface [Name] extends Remote {
    // ví dụ
    int fibonacci(int n) throws RemoteException;
    
    // mẫu
    (kiểu dữ liệu trả về) tên hàm (tham số) throws RemoteException;
}
```
# Remote[Tên Interface]Server.java
```java
public class Remote[Name]Server implements [Name] {
    // ví dụ
    @Override
    public int fibonacci(int x) throws RemoteException {
        // logic của tính năng
        return x <= 1 ? x : fibonacci(x - 1) + fibonacci(x - 2);
    }
    
    // mẫu
    @Override
    public (copy toàn bộ định nghĩa của interface vào đây) throws RemoteException {
        // hàm làm gì đó
        return "foo bar";
    }
}
```

# Bài tập 1:

1. Viết interface và remote server cho để tính giai thừa n.
2. Viết interface và remote server cho để đếm xem trong chuỗi có bao nhiêu từ.

Hãy tự viết code trước khi xem đáp án. 🤖👺

<details>
<summary>Đáp án</summary>

### Bài 1 : Giai thừa

<details>
<summary>Cho tôi full bài 1</summary>

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GiaiThua extends Remote {
    int giaiThua(int n) throws RemoteException;
}
````

```java
import java.rmi.RemoteException;

public class RemoteGiaiThuaServer implements GiaiThua {

    @Override
    public int giaiThua(int n) throws RemoteException {
        if (n == 0 || n == 1)
            return 1;
        return n * giaiThua(n - 1);
    }
}
```

</details>

---

### Bài 2 : Đếm số từ

<details>
<summary>Cho tôi full bài 2</summary>

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DemTu extends Remote {
    int demTu(String s) throws RemoteException;
}
```

```java
import java.rmi.RemoteException;

public class RemoteDemTuServer implements DemTu {

    @Override
    public int demTu(String s) throws RemoteException {
        if (s == null || s.trim().isEmpty())
            return 0;

        String[] words = s.trim().split("\\s+");
        return words.length;
    }
}
```

</details>
</details>

# ServerMain.java  
```java
public class ServerMain {
    // định nghĩa tên sv cái này QUAN TRỌNG
    public static final String NAME = "server.ptb2";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        // vd
        final RemotePTB2Server server = new RemotePTB2Server();
        final Registry registry = LocateRegistry.createRegistry(2732);
        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(NAME, stub);
        Thread.sleep(Integer.MAX_VALUE);

        // mẫu
        final (RemoteServer muốn dùng) server = new [...] ();

        // đống này copy dùng lại chứ cx ko có j đặc sắc (cố mà thuộc :)) ) 
        final Registry registry = LocateRegistry.createRegistry(2732);
        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(NAME, stub);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
```

# ClientMain.java
```java
public class ClientMain {
    // định nghĩa tên sv cái này QUAN TRỌNG
    public static final String NAME = "server.ptb2";

    public static void main(String[] args) throws RemoteException, NotBoundException {
        // vd này đc cắt bớt so với code gốc tham khảo full code trong folder RMI_ptb2
        final Registry registry = LocateRegistry.getRegistry(2732);

        PTB2 ptb2 = (PTB2) registry.lookup(NAME);
        try {
            String Result = ptb2.giaiPTB2(1,2,3);
            System.out.println("Kết quả: " + Result);
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập một số nguyên hợp lệ hoặc 'x' để thoát.");
        }

        // tóm lại trong main thì có 3 phần 
        // 1 là đăng ký cổng
        final Registry registry = LocateRegistry.getRegistry(2732);
        // 2 là đăng ký interface
        Interface i = (Interface) registry.lookup(NAME);
        // 3 là dùng 
        int a = i.foo();
    }
}
```

# BT (Code full từ đầu đến đít nhá :3 code bằng giấy thì càng vjp ) (đáp án sẽ cập nhật sau)

<p align="center">
    <img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExZDBvMjJvNDcxbTVmeWNibDN0cW90NjB2aXFiZng0c2g4aDZsaXQ4bCZlcD12MV9naWZzX3NlYXJjaCZjdD1n/uDZexRVCffGww/giphy.gif"/>
</p>

### 1. [Tính tổng 2 số](https://github.com/gang-gang-67-rau-ma/lap-trinh-mang-java/tree/main/RMI/RMI_1)
### 2. Kiểm tra số chẵn
### 3. Máy tính đơn giản
### 4. Giải phương trình bậc 1
### 5. [Giải phương trình bậc 2](https://github.com/gang-gang-67-rau-ma/lap-trinh-mang-java/tree/main/RMI/RMI_ptb2)