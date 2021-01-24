package pl.paweln.agricola.player;

public class Resource {
    private int value;
    private ResourceType type;

    public Resource(ResourceType type) {
        this.type = type;
        this.value = 0;
    }

    public void setValue(int value) {
        if (value < 0)
            throw new IllegalArgumentException("Resource value may never be negative");
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public ResourceType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
