package dew;

import dew.places.Basement;
import dew.plants.Strains;

import java.util.Vector;

public class Player {
    private final Basement basement;
    private final Vector<Strains> harvests;
    private double money;
    private int waterBill;

    public Player() {
        this.money = 1000;
        this.harvests = new Vector<>();
        this.basement = new Basement();
        this.waterBill = 0;
    }

    public void payWaterBill() {
        if (money >= waterBill) {
            money -= waterBill;
        } else {
            throw new IllegalArgumentException("Not enough money to pay the water bill");
        }
    }

    public void displayHarvests() {
        System.out.println("Your harvests:");
        for (int i = 0; i < getHarvests().size(); i++) {
            Strains strain = getHarvests().get(i);
            System.out.println(i + ". " + strain.name() + " harvest - Price: " + strain.getPrice());
        }
    }

    public Basement getBasement() {
        return basement;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money >= 0) {
            this.money = money;
        } else {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    public void pay(int amount) {
        if (money >= amount) {
            money -= amount;
        } else {
            throw new IllegalArgumentException("Not enough money to pay");
        }
    }

    public Vector<Strains> getHarvests() {
        return harvests;
    }

    public void addHarvest(Strains strain) {
        this.harvests.add(strain);
    }

    public void removeHarvest(Strains strain) {
        this.harvests.remove(strain);
    }

    public void increaseWaterBill(int amount) {
        this.waterBill += amount;
    }
}
