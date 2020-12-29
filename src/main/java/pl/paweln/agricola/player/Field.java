package pl.paweln.agricola.player;

public class Field {
    private final int x;
    private final int y;
    private FieldType type;
    private boolean hasLeftFence;
    private boolean hasRightFence;
    private boolean hasTopFence;
    private boolean hasBottomFence;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = FieldType.EMPTY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public boolean hasLeftFence() {
        return hasLeftFence;
    }

    public void setLeftFence(boolean hasLeftFence) {
        this.hasLeftFence = hasLeftFence;
    }

    public boolean hasRightFence() {
        return hasRightFence;
    }

    public void setRightFence(boolean hasRightFence) {
        this.hasRightFence = hasRightFence;
    }

    public boolean hasTopFence() {
        return hasTopFence;
    }

    public void setTopFence(boolean hasTopFence) {
        this.hasTopFence = hasTopFence;
    }

    public boolean hasBottomFence() {
        return hasBottomFence;
    }

    public void setBottomFence(boolean hasBottomFence) {
        this.hasBottomFence = hasBottomFence;
    }
}
