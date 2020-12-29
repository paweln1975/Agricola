package pl.paweln.agricola.player;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private final Field[][] fields = new Field[5][3];
    private final List<Field> fieldList = new LinkedList<>();

    private HouseType houseType;

    public Board() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = new Field(i+1, j+1);
                this.fieldList.add(fields[i][j]);
            }
        }
        getField(1, 1).setType(FieldType.HOUSE);
        getField(1, 2).setType(FieldType.HOUSE);
    }

    public Field getField(int number) {
        if (number <= 0 || number > this.fieldList.size())
            throw new IllegalArgumentException("Board games has 15 fields, but requested is:" + number);
        return this.fieldList.get(number-1);
    }

    public Field getField(int x, int y) {
        if (x <= 0 || x > 5)
            throw new IllegalArgumentException("Illegal reference to the player board. Board has 5 fields width");
        if (y <= 0 || y > 3)
            throw new IllegalArgumentException("Illegal reference to the player board. Board has 3 fields height");
        return fields[x-1][y-1];
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public int getHouseSize() {
        int size = 0;
        for (Field f : fieldList)
            if (f.getType() == FieldType.HOUSE)
                size++;
        return size;
    }
}
