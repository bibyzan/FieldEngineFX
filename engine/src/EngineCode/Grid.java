package EngineCode;

import java.util.HashMap;

public class Grid<V> extends HashMap<Position, V> {
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
}