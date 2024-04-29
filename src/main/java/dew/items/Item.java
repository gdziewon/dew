package dew.items;

public abstract class Item {
    protected int price;
    protected String type;

    public Item(int price, String type) {
        this.price = price;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public abstract void use(Pot pot);
}