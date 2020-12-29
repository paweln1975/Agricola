package pl.paweln.agricola.player;

import java.util.EnumMap;
import java.util.Map;

public class Player {
    private String name;
    private Color color;
    private final Board board;

    private int totalActionCount;
    private int availableActionCount;

    private final Map<ResourceType, Resource> resources =
            new EnumMap<>(ResourceType.class);

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        totalActionCount = 2;
        availableActionCount = 2;

        Resource[] res = new Resource[] {
                new Resource(ResourceType.WOOD),
                new Resource(ResourceType.CLAY),
                new Resource(ResourceType.STONE),
                new Resource(ResourceType.REED),
                new Resource(ResourceType.SHEEP),
                new Resource(ResourceType.WILD_BOAR),
                new Resource(ResourceType.COW),
                new Resource(ResourceType.FOOD),
        };

        for (Resource resource : res) {
            this.resources.put(resource.getType(), resource);
        }

    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return this.name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public int getTotalActionCount() {
        return totalActionCount;
    }

    public int getAvailableActionCount() {
        return availableActionCount;
    }

    public int getResourceAmount(ResourceType resourceType) {
        return this.resources.get(resourceType).getValue();
    }

    public void setResourceAmount(ResourceType resourceType, int value) {
        this.resources.get(resourceType).setValue(value);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", totalActionCount=" + totalActionCount +
                ", availableActionCount=" + availableActionCount +
                '}';
    }
}
