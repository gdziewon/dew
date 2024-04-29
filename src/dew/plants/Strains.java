package dew.plants;

import dew.items.PricedItem;

public enum Strains implements PricedItem {
    BUSH(30, new int[]{10, 20}),
    JFK(40, new int[]{15, 25}),
    OBAMA(60, new int[]{20, 30}),
    TRUMP(88, new int[]{25, 35}),
    BIDEN(70, new int[]{30, 40});

    private final int growthRequired;
    private final int[] valueRange;

    Strains(int growthRequired, int[] valueRange) {
        this.growthRequired = growthRequired;
        this.valueRange = valueRange;
    }

    public int getGrowthRequired() {
        return growthRequired;
    }

    public int[] getValueRange() {
        return valueRange;
    }

    @Override
    public int getPrice() {
        return valueRange[0] / 2;
    }
}
