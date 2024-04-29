package dew.plants;

public class Plant {
    private final Strains strainType;
    private int growthStage;
    private int health;

    public Plant(Strains strainType) {
        this.strainType = strainType;
        this.growthStage = 0;
        this.health = 100;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void advanceGrowthStage(int bonus) {
        this.growthStage += bonus;
        if (this.growthStage > strainType.getGrowthRequired()) {
            this.growthStage = strainType.getGrowthRequired();
        }
    }

    public Strains getStrainType() {
        return strainType;
    }

    public int getGrowthStage() {
        return growthStage;
    }

    public boolean isFullyGrown() {
        return growthStage >= strainType.getGrowthRequired();
    }

    public void increaseHealth(int amount) {
        this.health += amount;
        if (this.health > 100) {
            this.health = 100;
        }
    }

    public boolean die() {
        if (this.health <= 0) {
            System.out.println("The plant has died.");
            return true;
        }
        return false;
    }
}
