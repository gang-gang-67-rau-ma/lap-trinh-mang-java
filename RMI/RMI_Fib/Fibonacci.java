package RMI_Fib;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Fibonacci extends Remote {
    int fibonacci(int n) throws RemoteException;
}
