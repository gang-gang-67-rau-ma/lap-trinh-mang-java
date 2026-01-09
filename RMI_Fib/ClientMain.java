package RMI_Fib;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMain {
    public static final String NAME = "server.fibonacci";

    public static void main(String[] args) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(2732);
        Fibonacci fib = (Fibonacci) registry.lookup(NAME);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Nhập một chuỗi (gõ '!' để thoát): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("!")) {
                System.out.println("Thoát chương trình.");
                break;
            }

            try {
                int number = Integer.parseInt(input);
                int fibResult = fib.fibonacci(number);
                System.out.println("Fibonacci của " + number + " là: " + fibResult);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ hoặc '!' để thoát.");
            }
        }
    }
}
