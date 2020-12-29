package pl.paweln.agricola.player;

public class Field {
    private final int x;
    private final int y;
    private FieldType type;
    boolean hasLeftFence;
    boolean hasRightFence;
    boolean hasTopFence;
    boolean hasBottomFence;

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

    public boolean isHasLeftFence() {
        return hasLeftFence;
    }

    public void setHasLeftFence(boolean hasLeftFence) {
        this.hasLeftFence = hasLeftFence;
    }

    public boolean isHasRightFence() {
        return hasRightFence;
    }

    public void setHasRightFence(boolean hasRightFence) {
        this.hasRightFence = hasRightFence;
    }

    public boolean isHasTopFence() {
        return hasTopFence;
    }

    public void setHasTopFence(boolean hasTopFence) {
        this.hasTopFence = hasTopFence;
    }

    public boolean isHasBottomFence() {
        return hasBottomFence;
    }

    public void setHasBottomFence(boolean hasBottomFence) {
        this.hasBottomFence = hasBottomFence;
    }
}
