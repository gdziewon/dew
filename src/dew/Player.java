package dew;

import dew.items.Pot;
import dew.places.Basement;
import dew.plants.Harvest;

import java.util.Vector;

public class Player {
    private final String name;
    private final Basement basement;
    private final Vector<Harvest> harvests;
    private double money;
    private int waterBill;

    public Player(String name) {
        this.name = name;
        this.money = 1000;
        this.harvests = new Vector<>();
        this.basement = new Basement();
        this.basement.addPot(new Pot());
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
            Harvest harvest = getHarvests().get(i);
            System.out.println(i + ". " + harvest.strainType().name() + " harvest - Price: " + harvest.price());
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

    public Vector<Harvest> getHarvests() {
        return harvests;
    }

    public void addHarvest(Harvest harvest) {
        this.harvests.add(harvest);
    }

    public void removeHarvest(Harvest harvest) {
        this.harvests.remove(harvest);
    }

    public String getName() {
        return name;
    }

    public void increaseWaterBill(int amount) {
        this.waterBill += amount;
    }
}
