package dew.items;

import dew.plants.Plant;
import dew.plants.Seed;
import dew.plants.Strains;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pot implements PricedItem {
    private final List<Tool> tools;
    private Plant plant;

    public Pot() {
        this.plant = null;
        this.tools = new ArrayList<>();
    }

    public boolean isEmpty() {
        return plant == null;
    }

    public void plantSeed(Seed seed) {
        if (isEmpty()) {
            this.plant = new Plant(seed.getStrainType());
        } else {
            throw new IllegalStateException("Pot is not empty");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pot - ");
        if (isEmpty()) {
            sb.append("Empty");
        } else {
            sb.append("Contains ").append(plant.getStrainType().name()).append(" plant");
            sb.append("\nThe plant's health is ").append(plant.getHealth());
            sb.append("\nThe plant's growth stage is ").append(plant.getGrowthStage());
        }
        if (!tools.isEmpty()) {
            sb.append("\nThe pot has the following tools:");
            for (Tool tool : tools) {
                sb.append("\n- ").append(tool.getType());
                sb.append(" (Durability: ").append(tool.getDurability()).append(")");
            }
        }
        return sb.toString();
    }

    public Strains harvestPlant() {
        if (isEmpty()) {
            System.out.println("This pot is empty.");
            return null;
        }
        if (plant.isFullyGrown()) {
            Strains strain = plant.getStrainType();
            this.plant = null;
            return strain;
        } else {
            System.out.println("The plant is not fully grown.");
            return null;
        }
    }

    public Plant getPlant() {
        return plant;
    }

    public void tick() {
        if (isEmpty()) {
            return;
        }

        plant.advanceGrowthStage(1);
        plant.decreaseHealth(1);

        if (plant.die()) {
            plant = null;
            return;
        }

        Iterator<Tool> toolIterator = tools.iterator();
        while (toolIterator.hasNext()) {
            Tool tool = toolIterator.next();
            if (tool.getDurability() > 0) {
                plant.advanceGrowthStage(tool.getToolType().getBonus());
                tool.setDurability(tool.getDurability() - 1);
            } else {
                toolIterator.remove();
            }
        }
    }

    public boolean waterPlant() {
        if (!isEmpty()) {
            getPlant().increaseHealth(10);
            System.out.println("You watered a plant!");
            return true;
        } else {
            System.out.println("This pot is empty.");
            return false;
        }
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }

    @Override
    public int getPrice() {
        return 50;
    }

    @Override
    public String name() {
        return "Pot";
    }
}
