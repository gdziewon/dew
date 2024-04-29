// Street.java
package dew.places;

import dew.Customer;
import dew.Player;
import dew.plants.Harvest;
import dew.plants.StrainPrice;
import dew.plants.Strains;

import java.util.Scanner;
import java.util.Vector;

public class Street {
    private final Vector<Customer> customers;
    private final StrainPrice[] strainPrices;
    private final Scanner scanner;

    public Street() {
        this.strainPrices = new StrainPrice[Strains.values().length];
        for (int i = 0; i < Strains.values().length; i++) {
            this.strainPrices[i] = new StrainPrice(Strains.values()[i]);
        }
        this.customers = new Vector<>();
        this.scanner = new Scanner(System.in);
    }

    public void displayCustomers() {
        System.out.println("Your customers:");
        for (int i = 0; i < getCustomers().size(); i++) {
            Customer customer = getCustomers().get(i);
            System.out.println(i + ". Customer - Funds: " + customer.getFunds());
        }
    }

    public void setStrainPrices() {
        System.out.println("Current prices:");
        for (StrainPrice strainPrice : strainPrices) {
            System.out.println(strainPrice.getStrain().name() + ": " + strainPrice.getPrice());
        }
        for (StrainPrice strainPrice : strainPrices) {
            System.out.println("Enter new price for " + strainPrice.getStrain().name() + ":");
            int newPrice = scanner.nextInt();
            strainPrice.setPrice(newPrice);
        }
    }

    public void sellHarvest(Player player) {
        System.out.println("Choose a harvest to sell:");
        int harvestIndex = scanner.nextInt();
        if (harvestIndex >= 0 && harvestIndex < player.getHarvests().size()) {
            Harvest harvest = player.getHarvests().get(harvestIndex);
            for (Customer customer : customers) {
                if (customer.getFunds() >= harvest.price()) {
                    customer.setFunds(customer.getFunds() - harvest.price());
                    player.setMoney(player.getMoney() + harvest.price());
                    player.removeHarvest(harvest);
                    System.out.println("You sold a " + harvest.strainType().name() + " harvest!");
                    return;
                }
            }
            System.out.println("No customer can afford this harvest.");
        } else {
            System.out.println("Invalid harvest index.");
        }

    }

    public Vector<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }
}