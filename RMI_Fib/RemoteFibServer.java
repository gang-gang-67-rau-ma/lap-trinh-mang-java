package RMI_Fib;

import java.rmi.RemoteException;

public class RemoteFibServer implements Fibonacci {
    @Override
    public int fibonacci(int x) throws RemoteException {
        return x <= 1 ? x : fibonacci(x - 1) + fibonacci(x - 2);
    }
}
