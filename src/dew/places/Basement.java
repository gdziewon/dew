package dew.places;

import dew.items.Consumable;
import dew.items.Item;
import dew.items.Pot;
import dew.items.Tool;
import dew.plants.Seed;

import java.util.Scanner;
import java.util.Vector;

public class Basement {
    private final Vector<Item> items;
    private final Vector<Pot> pots;
    private final Vector<Seed> seeds;
    private final Scanner scanner;

    public Basement() {
        this.items = new Vector<>();
        this.pots = new Vector<>();
        this.seeds = new Vector<>();
        this.scanner = new Scanner(System.in);
        addPot(new Pot());
    }

    public void useTool(Pot pot) {
        if (getItems().stream().noneMatch(item -> item instanceof Tool)) {
            System.out.println("You don't have any tools.");
            return;
        }

        displayItems(Tool.class);
        int toolIndex = scanner.nextInt();
        if (toolIndex >= 0 && toolIndex < getItems().size()) {
            Item item = getItems().get(toolIndex);
            if (item instanceof Tool) {
                item.use(pot);
                getItems().remove(item);
                System.out.println("You used a " + item.getType() + " tool!");
            } else {
                System.out.println("This item is not a tool.");
            }
        } else {
            System.out.println("Invalid tool index.");
        }
    }

    public void useConsumable(Pot pot) {
        if (getItems().stream().noneMatch(item -> item instanceof Consumable)) {
            System.out.println("You don't have any consumables.");
            return;
        }

        if (!pot.isEmpty() && pot.getPlant().isFullyGrown()) {
            System.out.println("The plant is fully grown and doesn't need more consumables.");
            return;
        }

        displayItems(Consumable.class);
        int consumableIndex = scanner.nextInt();
        if (consumableIndex >= 0 && consumableIndex < getItems().size()) {
            Item item = getItems().get(consumableIndex);
            if (item instanceof Consumable) {
                item.use(pot);
                getItems().remove(item);
                System.out.println("You used a " + item.getType() + " consumable!");
            } else {
                System.out.println("This item is not a consumable.");
            }
        } else {
            System.out.println("Invalid consumable index.");
        }
    }

    public void displayPots() {
        System.out.println("Pots in the basement:");
        for (int i = 0; i < getPots().size(); i++) {
            Pot pot = getPots().get(i);
            String potInfo = pot.isEmpty() ? "Empty" : "Contains " + pot.getPlant().getStrainType().name() + " plant";
            System.out.println(i + ". Pot - " + potInfo);
        }
        System.out.println("Choose a pot to interact with, or -1 to exit:");
    }

    public void displaySeeds() {
        System.out.println("Choose a seed to plant:");
        for (int i = 0; i < getSeeds().size(); i++) {
            Seed seed = getSeeds().get(i);
            System.out.println(i + ". " + seed.getStrainType().name() + " seed");
        }
    }

    private void displayItems(Class<?> clazz) {
        System.out.println("Choose a " + clazz.getSimpleName().toLowerCase() + " to use:");
        int index = 0;
        for (Item item : getItems()) {
            if (clazz.isInstance(item)) {
                System.out.println(index + ". " + item.getType() + " " + clazz.getSimpleName().toLowerCase());
                index++;
            }
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addPot(Pot pot) {
        this.pots.add(pot);
    }

    public void addSeed(Seed seed) {
        this.seeds.add(seed);
    }

    public Vector<Pot> getPots() {
        return pots;
    }

    public Vector<Seed> getSeeds() {
        return seeds;
    }

    public Vector<Item> getItems() {
        return items;
    }
}