package model;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Scanner;

import model.Toy;

public class ToyMahchine {
    private int id;
    private String name;
    private ArrayList<Toy> toys;
    private Logger logger;
    private ArrayList<Double> probability;
    private double totalWeight;


    public ToyMahchine(String name) {
        this.name = name;
        this.id = 0;
        this.toys = new ArrayList<>();
        this.logger = Logger.getAnonymousLogger();
        this.probability = new ArrayList<>();
        this.totalWeight = 0;
        
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getToysString() {
        StringBuilder content = new StringBuilder();
        for (Toy toy : this.toys) {
            content.append(toy.toString());
            content.append(". ");
        }
        return content.toString().trim();
    }

    private void updateWeight() {
        this.probability = new ArrayList<>();
        this.totalWeight = 0;
        double mss = 0;
        for (Toy toy : this.toys) {
            mss = toy.getQuantity() * toy.getWeight();
            this.totalWeight = this.totalWeight + mss;
            this.probability.add(mss);

        }
        for (Double prob : this.probability) {
            prob = prob / this.totalWeight;            
        }
    }

    public void addToy(String name, int quantity, double weight) {
        this.toys.add(new Toy(this.id++, name, quantity, weight));
        updateWeight();
    }

    private void updateQnt(int id) {
        Scanner scanner = new Scanner(System.in);
        for (Toy toy : this.toys) {
            if (toy.getId() != id) {
                continue;
            }
            System.out.println("Current quantity is " + toy.getQuantity() + "Input the new quantity (positive): ");
            while (!scanner.hasNextInt() || scanner.nextInt() <= 0) {
                System.out.println("Invalid input. Please enter a positive integer: ");
                scanner.next();
            }
            int newQ = scanner.nextInt();
            toy.setQuantity(newQ);
            scanner.close();
            break;
        }
        return;
    }

    
    private void updateWgh(int id) {
        Scanner scanner = new Scanner(System.in);
        for (Toy toy : this.toys) {
            if (toy.getId() != id) {
                continue;
            }
            System.out.println("Current weight of the toy "+toy.getName() +" is " + toy.getWeight() + "Input the new weight (positive double): ");
            while (!scanner.hasNextDouble() || scanner.nextDouble() <= 0) {
                System.out.println("Invalid input. Please enter a positive double: ");
                scanner.next();
            }
            double newW = scanner.nextDouble();
            toy.setWeight(newW);
            scanner.close();
            break;
        }
        return;
    }

    private Boolean checkId(int id) {
        Boolean isId = false;
        for (Toy toy : this.toys) {
            if (toy.getId() == id) {
                isId = true;
                break;
            }            
        }
        return isId;
    }


    public void updateToy(int id, String mode) {
        if (checkId(id) == false) {
            this.logger.info("Toys with given ID are not presented.");
            return;
        }
        if (mode.toLowerCase().equals("q")) {
            updateQnt(id);
        }

        if (mode.toLowerCase().equals("w")) {
            updateWgh(id);
        }
    }

    public void deleteToy(int id) {
        if (checkId(id)) {
            for (Toy toy : this.toys) {
                if (toy.getId() == id) {
                    this.toys.remove(toy);
                    break;
            }    
            }
            
        }
    }

}