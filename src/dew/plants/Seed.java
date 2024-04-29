package dew.plants;

import dew.items.Item;
import dew.items.Pot;

public class Seed extends Item {
    private final Strains strainType;
    private final int price;

    public Seed(Strains strainType, int price) {
        super(price, strainType.name());
        this.strainType = strainType;
        this.price = price;
    }

    public Strains getStrainType() {
        return strainType;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void use(Pot pot) {
        if (pot.isEmpty()) {
            pot.plantSeed(this);
            System.out.println("You planted a " + strainType.name() + " seed!");
        } else {
            System.out.println("This pot is not empty.");
        }
    }
}