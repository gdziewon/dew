package dew.items;

public enum Consumables {
    FERTILIZER(20, 5),
    PESTICIDE(30, 10);

    private final int price;
    private final int bonus;

    Consumables(int price, int bonus) {
        this.price = price;
        this.bonus = bonus;
    }

    public int getPrice() {
        return price;
    }

    public int getBonus() {
        return bonus;
    }
}