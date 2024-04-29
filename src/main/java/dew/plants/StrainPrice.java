package dew.plants;

public class StrainPrice {
    private final Strains strain;
    private int price;

    public StrainPrice(Strains strain) {
        this.strain = strain;
        this.price = strain.getValueRange()[0];
    }

    public Strains getStrain() {
        return strain;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        int[] range = strain.getValueRange();
        if (price >= range[0] && price <= range[1]) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be within the value range of the strain");
        }
    }
}