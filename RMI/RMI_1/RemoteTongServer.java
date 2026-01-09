package RMI_1;

public class RemoteTongServer implements Tong {
    @Override
    public int tong (int a, int b) {
        return a+b;
    }
}