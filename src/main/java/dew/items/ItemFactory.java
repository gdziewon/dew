package dew.items;

import dew.plants.Seed;
import dew.plants.Strains;

public class ItemFactory {

    public static Item createItem(PricedItem pricedItem) {
        if (pricedItem instanceof Tools) {
            return new Tool((Tools) pricedItem);
        } else if (pricedItem instanceof Consumables) {
            return new Consumable((Consumables) pricedItem);
        } else if (pricedItem instanceof Strains) {
            return new Seed((Strains) pricedItem);
        } else {
            throw new IllegalArgumentException("Invalid item type");
        }
    }
}