package dew.items;

public class Consumable extends Item {
    private final Consumables consumableType;

    public Consumable(Consumables consumableType) {
        super(consumableType.getPrice(), consumableType.name());
        this.consumableType = consumableType;
    }

    @Override
    public void use(Pot pot) {
        if (!pot.isEmpty() && !pot.getPlant().isFullyGrown()) {
            pot.getPlant().increaseHealth(consumableType.getBonus());
            System.out.println("You used a " + consumableType.name() + " consumable on a plant!");
        } else {
            System.out.println("The pot is empty or the plant is fully grown.");
        }
    }
}