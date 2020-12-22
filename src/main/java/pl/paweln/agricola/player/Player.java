package pl.paweln.agricola.player;

public class Player {
    private String name;
    private Color color;

    private int totalActionCount;
    private int availableActionCount;

    public Player(String name) {
        this.name = name;
        totalActionCount = 2;
        availableActionCount = 2;
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
