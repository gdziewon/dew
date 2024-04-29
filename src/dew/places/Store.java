package dew.places;

import dew.Player;
import dew.items.*;
import dew.plants.Seed;
import dew.plants.Strains;

import java.util.Vector;

public class Store {
    private final Vector<Item> items;
    private final Vector<Pot> pots;
    private final Vector<Seed> seeds;

    public Store() {
        this.items = new Vector<>();
        this.pots = new Vector<>();
        this.seeds = new Vector<>();
        initializeStore();
    }

    private void initializeStore() {
        // Add each type of tool and consumable to the store
        for (Tools toolType : Tools.values()) {
            items.add(new Tool(toolType));
        }
        for (Consumables consumableType : Consumables.values()) {
            items.add(new Consumable(consumableType, 1));
        }
        // Add pots to the store
        for (int i = 0; i < 10; i++) {
            pots.add(new Pot());
        }
        // Add seeds to the store
        for (Strains strainType : Strains.values()) {
            seeds.add(new Seed(strainType, (int) (strainType.getValueRange()[0] * 0.5)));
        }
    }

    public void displayStoreItems() {
        System.out.println("Items in the store:");
        for (int i = 0; i < getItems().size(); i++) {
            Item item = getItems().get(i);
            System.out.println(i + ". " + item.getType() + " - Price: " + item.getPrice());
        }
        if (!getPots().isEmpty()) {
            System.out.println(getItems().size() + ". Pot - Price: 50");
        }
        for (int i = 0; i < getSeeds().size(); i++) {
            Seed seed = getSeeds().get(i);
            System.out.println((getItems().size() + i + 1) + ". " + seed.getStrainType().name() + " seed - Price: " + seed.getPrice());
        }
        System.out.println("Choose an item to buy or -1 to exit:");
    }

    public Vector<Item> getItems() {
        return items;
    }

    public Vector<Pot> getPots() {
        return pots;
    }

    public void buyItem(Player player, Item item) {
        if (player.getMoney() >= item.getPrice()) {
            player.setMoney(player.getMoney() - item.getPrice());
            if (item instanceof Tool) {
                player.getBasement().addItem(new Tool(((Tool) item).getToolType()));
            } else if (item instanceof Consumable) {
                player.getBasement().addItem(new Consumable(((Consumable) item).getConsumableType(), ((Consumable) item).getQuantity()));
            }
        } else {
            throw new IllegalArgumentException("Not enough money to buy this item");
        }
    }

    public void buyPot(Player player, Pot pot) {
        if (player.getMoney() >= 50) {
            player.setMoney(player.getMoney() - 50);
            player.getBasement().addPot(new Pot());
            pots.remove(pot);
        } else {
            throw new IllegalArgumentException("Not enough money to buy a pot");
        }
    }

    public void buySeed(Player player, Seed seed) {
        if (player.getMoney() >= seed.getPrice()) {
            player.setMoney(player.getMoney() - seed.getPrice());
            player.getBasement().addSeed(new Seed(seed.getStrainType(), seed.getPrice()));
        } else {
            throw new IllegalArgumentException("Not enough money to buy this seed");
        }
    }

    public Vector<Seed> getSeeds() {
        return seeds;
    }
}