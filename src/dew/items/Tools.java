package dew.items;

public enum Tools implements PricedItem {
    LAMP(10, 100, 1),
    EPIC_LAMP(5, 200, 2),
    FAN(15, 150, 2),
    COOL_FAN(10, 300, 3);

    private final int currentIntake;
    private final int price;
    private final int bonus;

    Tools(int currentIntake, int price, int bonus) {
        this.currentIntake = currentIntake;
        this.price = price;
        this.bonus = bonus;
    }

    public int getCurrentIntake() {
        return currentIntake;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public int getBonus() {
        return bonus;
    }
}