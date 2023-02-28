package control;
import model.*;
import java.util.Scanner;

import model.*;

public class Control {
    

    public static void run() {
        ToyMahchine machine = new ToyMahchine("my");
        while (true) {

            System.out.println("Input command (add, change, get, list, exit):");
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextLine()) {
                case "add":
                
                    System.out.println("Input the name: ");
                    String nameToy = scanner.nextLine();
                   
                    System.out.println("Input the new quantity (positive): ");
                    while (!scanner.hasNextInt() || scanner.nextInt() <= 0) {
                    System.out.println("Invalid input. Please enter a positive integer: ");
                    scanner.next();
                    }
                    int quantity = scanner.nextInt();


                    System.out.println("Input the new weight (positive double): ");
                    while (!scanner.hasNextDouble() || scanner.nextDouble() <= 0) {
                       System.out.println("Invalid input. Please enter a positive double: ");
                        scanner.next();
                    }
                    double weight = scanner.nextDouble();

                    machine.addToy(nameToy, quantity, weight);
                    break;
                case "exit":
                    System.exit(0);
                case "change":
                    System.out.println("Input id to change");
                    while (!scanner.hasNextInt() || scanner.nextInt() < 0) {
                    System.out.println("Invalid input. Please enter a non-negatie integer: ");
                    scanner.next();
                    }
                    int id = scanner.nextInt();
                    
                    System.out.println("'q' to change quantity, 'w' to change wegiht:" );
                    String input = scanner.next();
                    while (!input.equals("q") && !input.equals("w")) {
                        System.out.print("Invalid input. Please enter 'q' or 'w': ");
                        input = scanner.next();
                    }
                    machine.updateToy(id, input);
                    
                case "delete":
                    System.out.println("Input id to delete");
                    while (!scanner.hasNextInt() || scanner.nextInt() < 0) {
                    System.out.println("Invalid input. Please enter a non-negatie integer: ");
                    scanner.next();
                    }
                    id = scanner.nextInt();
                    


                default:
                    System.out.println("Invalid command");
            }

        }
        
    
}

}
