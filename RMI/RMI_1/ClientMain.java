package RMI_1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain{
    public static final string NAME = "abc";

    public static void main(String[] args) throws RemoteException,NotBoundException{
        final Registry registry = LocateRegistry.getRegistry(4444);
        Tong tong = (Tong) registry.lookup(NAME);
        int fin = tong.tong(3, 6);
        System.out.println("kqua : "+ fin);
    }
}