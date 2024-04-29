package dew;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

import dew.items.Consumable;
import dew.items.Pot;
import dew.items.Tool;
import dew.places.Store;
import dew.places.Street;
import dew.plants.Seed;
import dew.plants.Strains;



public class Game {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final Player player;
    private final Store store;
    private final Street street;
    private final Scanner scanner;

    public Game() {
        this.player = new Player();
        this.store = new Store();
        this.street = new Street();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        new Thread(this::gameLoop).start();
        System.out.println("Welcome to main.java.dew!");
        boolean isRunning = true;
        while (isRunning) {
            isRunning = handleMainMenu();
        }
    }

    private boolean handleMainMenu() {
        System.out.println("Choose an action: 1. Go to store, 2. Go to basement, 3. Go to street, 4. Exit");
        int action = scanner.nextInt();
        switch (action) {
            case 1:
                goToStore();
                break;
            case 2:
                goToBasement();
                break;
            case 3:
                goToStreet();
                break;
            case 4:
                return false;
            default:
                System.out.println("Invalid action");
        }
        return true;
    }

    private void gameLoop() {
        final Runnable ticker = this::tick;
        scheduler.scheduleAtFixedRate(ticker, 0, 10, TimeUnit.SECONDS);
    }

    public void tick() {
        // Each pot has its own tick
        for (Pot pot : player.getBasement().getPots()) {
            pot.tick();
        }

        // The number of customers increases
        if (Math.random() < 0.1) { // 10% chance to get a new customer
            street.addCustomer(new Customer((int) (Math.random() * 1000), Math.random() < 0.5));
        }

        // Player pays electrical bill for each tool
        for (Pot pot : player.getBasement().getPots()) {
            for (Tool tool : pot.getTools()) {
                player.setMoney(player.getMoney() - tool.getToolType().getCurrentIntake() * 0.1);
            }
        }

        // Player pays water bill
        player.payWaterBill();
    }

    private void goToStore() {
        int itemIndex;
        do {
            System.out.println("You have " + player.getMoney() + " money.");
            store.displayStoreItems();
            itemIndex = scanner.nextInt();
            store.handleStoreAction(itemIndex, player);
        } while (itemIndex != -1);
    }


    private void goToBasement() {
        int potIndex;
        do {
            player.getBasement().displayPots();
            potIndex = scanner.nextInt();
            if (potIndex >= 0 && potIndex < player.getBasement().getPots().size()) {
                System.out.println("You chose pot " + potIndex + ".");
                Pot pot = player.getBasement().getPots().get(potIndex);
                interactWithPot(pot);
            } else if (potIndex != -1) {
                System.out.println("Invalid pot index.");
            }
        } while (potIndex != -1);
    }

    private void interactWithPot(Pot pot) {
        System.out.println(pot);
        System.out.println("Choose an action: ");
        System.out.println("1. Water the plant");
        if (pot.isEmpty() && !player.getBasement().getSeeds().isEmpty()) {
            System.out.println("2. Plant a seed");
        }
        if (player.getBasement().getItems().stream().anyMatch(item -> item instanceof Tool)) {
            System.out.println("3. Use a tool");
        }
        if (player.getBasement().getItems().stream().anyMatch(item -> item instanceof Consumable)) {
            System.out.println("4. Use a consumable");
        }
        if (!pot.isEmpty() && pot.getPlant().isFullyGrown()) {
            System.out.println("5. Harvest plant");
        }
        System.out.println("6. Go back");
        int action = scanner.nextInt();
        switch (action) {
            case 1:
                if (pot.waterPlant()) {
                    player.increaseWaterBill(1);
                }
                break;
            case 2:
                if (!player.getBasement().getSeeds().isEmpty()) {
                    plantSeed(pot);
                } else {
                    System.out.println("You don't have any seeds.");
                }
                break;
            case 3:
                player.getBasement().useTool(pot);
                break;
            case 4:
                player.getBasement().useConsumable(pot);
                break;
            case 5:
                Strains harvest = pot.harvestPlant();
                if (harvest != null) {
                    player.addHarvest(harvest);
                    System.out.println("You harvested a " + harvest.name() + " plant!");
                }
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid action");
        }
    }

    private void plantSeed(Pot pot) {
        if (pot.isEmpty()) {
            player.getBasement().displaySeeds();
            int seedIndex = scanner.nextInt();
            if (seedIndex >= 0 && seedIndex < player.getBasement().getSeeds().size()) {
                Seed seed = player.getBasement().getSeeds().get(seedIndex);
                pot.plantSeed(seed);
                player.getBasement().getSeeds().remove(seed);
                System.out.println("You planted a " + seed.getStrainType().name() + " seed!");
            } else {
                System.out.println("Invalid seed index.");
            }
        } else {
            System.out.println("This pot is not empty.");
        }
    }

    // Game.java
    private void goToStreet() {
        System.out.println("You are on the street.");
        if (!player.getHarvests().isEmpty() && !street.getCustomers().isEmpty()) {
            player.displayHarvests();
            street.displayCustomers();
        } else {
            System.out.println("You have no harvests to sell or no customers to sell to.");
            return;
        }

        System.out.println("Choose an action: 1. Sell harvest, 2. Set prices, 3. Go back");
        int action = scanner.nextInt();
        switch (action) {
            case 1:
                street.sellHarvest(player);
                break;
            case 2:
                street.setStrainPrices();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid action");
        }
    }
}
