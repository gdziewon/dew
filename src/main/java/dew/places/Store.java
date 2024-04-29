package dew.places;

import dew.Player;
import dew.items.*;
import dew.plants.Seed;
import dew.plants.Strains;

import java.util.Arrays;
import java.util.List;

public class Store {
    private final List<Tools> tools;
    private final List<Consumables> consumables;
    private final List<Strains> seeds;

    public Store() {
        this.tools = Arrays.asList(Tools.values());
        this.consumables = Arrays.asList(Consumables.values());
        this.seeds = Arrays.asList(Strains.values());
    }

    public void displayStoreItems() {
        System.out.println("Items in the store:");
        int totalItems = 0;
        totalItems = displayItems(tools, totalItems);
        totalItems = displayItems(consumables, totalItems);
        totalItems = displayItems(seeds, totalItems);
        System.out.println(totalItems + ". Pot - Price: 50");
        System.out.println("Choose an item to buy or -1 to exit:");
    }

    private int displayItems(List<? extends PricedItem> items, int startIndex) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((startIndex + i) + ". " + items.get(i).name() + " - Price: " + items.get(i).getPrice());
        }
        return startIndex + items.size();
    }

    public void buyItem(Player player, PricedItem item) {
        if (player.getMoney() >= item.getPrice()) {
            player.pay(item.getPrice());
            if (item instanceof Pot) {
                player.getBasement().addPot(new Pot()); // Handle pots separately
            } else {
                // Create the item and add it to the player's basement
                Item boughtItem = ItemFactory.createItem(item);
                if (item instanceof Tools || item instanceof Consumables) {
                    player.getBasement().addItem(boughtItem);
                } else if (item instanceof Strains) {
                    // Seeds are added to the basement's seed collection
                    player.getBasement().addSeed((Seed) boughtItem);
                }
            }
            System.out.println("You bought " + item.name() + "!");
        } else {
            System.out.println("You don't have enough money to buy this item.");
        }
    }

    // Handle player's choice of item to buy
    public void handleStoreAction(int itemIndex, Player player) {
        if (itemIndex >= 0 && itemIndex < tools.size()) {
            buyItem(player, tools.get(itemIndex));
        } else if (itemIndex >= tools.size() && itemIndex < tools.size() + consumables.size()) {
            buyItem(player, consumables.get(itemIndex - tools.size()));
        } else if (itemIndex >= tools.size() + consumables.size() && itemIndex < tools.size() + consumables.size() + seeds.size()) {
            buyItem(player, seeds.get(itemIndex - tools.size() - consumables.size()));
        } else if (itemIndex == tools.size() + consumables.size() + seeds.size() && player.getBasement().getPots().size() < 10) {
            buyItem(player, new Pot());
        } else if (itemIndex != -1) {
            System.out.println("Invalid item index.");
        }
    }
}