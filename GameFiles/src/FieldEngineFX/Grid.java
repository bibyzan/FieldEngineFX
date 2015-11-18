package FieldEngineFX;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid<V> extends HashMap<Position, V> {
    private int rowCount, colCount;

    public Grid() {
        super();

        rowCount = 0;
        colCount = 0;
    }

    @Override
    public V put(Position key, V value) {
        if (key.getCol() > colCount)
            colCount = key.getCol();
        if (key.getRow() > rowCount)
            rowCount = key.getRow();

        return super.put(key, value);
    }

    @Override
    public V get(Object key) {
        if (!(key instanceof Position))
            return null;

        Position keyPos = (Position)key;

        for (Position pos: super.keySet())
            if (pos.getCol() == keyPos.getCol() && pos.getRow() == keyPos.getRow())
                return super.get(pos);

        return null;
    }

    public ArrayList<V> getNearbyValues(Position p) {
        ArrayList<V> values = new ArrayList<>();

        V currentVal = super.get(p);
        if (currentVal != null)
            values.add(currentVal);

        for (Position nearbyPosition: p.getAdjacentPositions()) {
            V temp = super.get(nearbyPosition);

            if (temp != null)
                values.add(temp);
        }

        return values;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }
}