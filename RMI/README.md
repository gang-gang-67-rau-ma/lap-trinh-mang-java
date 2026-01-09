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
