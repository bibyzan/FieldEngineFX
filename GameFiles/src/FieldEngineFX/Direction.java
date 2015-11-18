package FieldEngineFX;

/**
 * 9/4/2015
 * This class is for use in the field node classes to help distinguish between
 * the different directions certain nodes may be facing to determine where they
 * go next when they move.
 *
 * The public statics of this class are the only things intended to be used.
 *
 * @author Ben Rasmussen
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public static Direction getOpposite(Direction direction) {
        if (direction.equals(UP))
            return DOWN;
        else if (direction.equals(DOWN))
            return UP;
        else if (direction.equals(RIGHT))
            return LEFT;
        else if (direction.equals(LEFT))
            return RIGHT;

        return ALL;
    }
}
