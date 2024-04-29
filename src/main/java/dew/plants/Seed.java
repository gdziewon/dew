package dew.plants;

import dew.items.Item;
import dew.items.Pot;

public class Seed extends Item {
    private final Strains strainType;

    public Seed(Strains strainType) {
        super(strainType.getPrice(), strainType.name());
        this.strainType = strainType;
    }

    public Strains getStrainType() {
        return strainType;
    }

    @Override
    public int getPrice() {
        return strainType.getPrice();
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