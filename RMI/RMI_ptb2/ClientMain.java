package RMI_ptb2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMain {
    public static final String NAME = "server.ptb2";

    public static void main(String[] args) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(2732);
        PTB2 ptb2 = (PTB2) registry.lookup(NAME);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Nhập bat ki de tiep tuc (gõ 'x' để thoát): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("x")) {
                System.out.println("Thoát chương trình.");
                break;
            }

            try {
                System.out.print("Nhập a: ");
                double a = Double.parseDouble(scanner.nextLine());

                System.out.print("Nhập b: ");
                double b = Double.parseDouble(scanner.nextLine());

                System.out.print("Nhập c: ");
                double c = Double.parseDouble(scanner.nextLine());

                String Result = ptb2.giaiPTB2(a, b, c);
                System.out.println("Kết quả: " + Result);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ hoặc 'x' để thoát.");
            }
        }
    }
}
