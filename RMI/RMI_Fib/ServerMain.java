package RMI_Fib;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static final String NAME = "server.fibonacci";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        final RemoteFibServer server = new RemoteFibServer();
        final Registry registry = LocateRegistry.createRegistry(2732);
        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(NAME, stub);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
