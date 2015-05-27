package EngineCode;

import java.util.ArrayList;

/**
 * Created by benra_000 on 5/15/2015.
 */
public final class Direction {
    public static final Direction UP = new Direction(0),
            RIGHT = new Direction(1),
            DOWN = new Direction(2),
            LEFT = new Direction(3),
            ALL = new Direction(4);

    private int direction;

    private Direction(int direction) {
        this.direction = direction;
    }

    public Direction(Direction direction) {
        this.direction = direction.getDirection();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            return true;
        else
            if (obj instanceof Direction) {
                Direction dir = (Direction)obj;

                return direction == dir.getDirection();
            } else
                return false;
    }

    public static ArrayList<Direction> getDirectionsFromSides(String sides) {
        ArrayList<Direction> sidesBlocked = new ArrayList<>();

        if (!sides.equals("N"))
            for (char c: sides.toCharArray())
                if (c == 'A')
                    sidesBlocked.add(Direction.ALL);
                else if (c == 'L' || c == 'W')
                    sidesBlocked.add(Direction.LEFT);
                else if (c == 'U' || c == 'N')
                    sidesBlocked.add(Direction.UP);
                else if (c == 'R' || c == 'E')
                    sidesBlocked.add(Direction.RIGHT);

        return sidesBlocked;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
