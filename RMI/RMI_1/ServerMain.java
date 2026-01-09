package RMI_1;

import java.rmi.*;

public class ServerMain {
    public static final string NAME = "abc";

    public static void main(String[] args) throws RemoteException,AlreadyBoundException,InterruptedException{
        final RemoteTongServer server = new RemoteTongServer();
        final Registry registry = LocateRegistry.createRegistry(4444);
        Remote stub = new UnicastRemoteObject.exportObject(server, 0);
        registry.bind(NAME, stub);
        Thread.sleep(Integer.MAX_VALUE);
    }
}