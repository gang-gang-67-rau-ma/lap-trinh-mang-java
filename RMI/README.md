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
        final Registry registry = LocateRegistry.createRegistry(số cổng bạn chọn);
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
        final Registry registry = LocateRegistry.getRegistry(số cổng bạn chọn);
        // 2 là đăng ký interface
        Interface i = (Interface) registry.lookup(NAME);
        // 3 là dùng 
        int a = i.foo();
    }
}
```

# BT
## **[Giải phương trình bậc 2](https://github.com/gang-gang-67-rau-ma/lap-trinh-mang-java/tree/main/RMI/RMI_ptb2)**