// Tool.java
package dew.items;

public class Tool extends Item {
    private final Tools toolType;
    private int durability;

    public Tool(Tools toolType) {
        super(toolType.getPrice(), toolType.name());
        this.toolType = toolType;
        this.durability = 100;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public Tools getToolType() {
        return toolType;
    }

    @Override
    public void use(Pot pot) {
        pot.addTool(this);
    }
}