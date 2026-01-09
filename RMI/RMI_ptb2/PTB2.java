package RMI_ptb2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PTB2 extends Remote {
    String giaiPTB2(double a, double b, double c) throws RemoteException;
}