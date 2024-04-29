package dew.items;

public class Consumable extends Item {
    private final Consumables consumableType;
    private int quantity;

    public Consumable(Consumables consumableType, int quantity) {
        super(consumableType.getPrice(), consumableType.name());
        this.consumableType = consumableType;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Consumables getConsumableType() {
        return consumableType;
    }

    @Override
    public void use(Pot pot) {
        if (!pot.isEmpty()) {
            pot.getPlant().advanceGrowthStage(consumableType.getBonus());
            System.out.println("You used " + consumableType.name() + " on a plant!");
            this.quantity--;
        } else {
            System.out.println("This pot is empty.");
        }
    }
}