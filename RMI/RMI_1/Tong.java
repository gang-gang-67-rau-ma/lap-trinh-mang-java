package RMI_1;

import java.rmi.*;

public interface Tong extends RemoteException {
    int tong (int a, int b) throws RemoteException;
}