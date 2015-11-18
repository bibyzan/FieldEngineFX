package FieldEngineFX;

/**
 * 9/15/2015
 * For use in all FieldNode subclasses. Four doubles are contained
 * here, usually the same or similar to the coordinates of the
 * FieldNode these Boundary objects are to be contained in.
 * This sole purpose of this class is to eventScript collisions on the
 * screen and help distinguish between regular graphics, and
 * graphics that the player isn't supposed to be able to step through.
 *
 * @author Ben Rasmussen
 */
public class Boundary {
    private double rightBound, leftBound, topBound, bottomBound;

    public Boundary(double rightBound, double leftBound, double topBound, double bottomBound) {
        this.rightBound = rightBound;
        this.leftBound = leftBound;
        this.topBound = topBound;
        this.bottomBound = bottomBound;
    }

    /**
     * By default, this objects private data is filled to contain
     * the same values as the node it is in. If it is discovered that
     * a player is being passed in, the player gets a slightly smaller
     * boundary and it's only half the size.
     *
     * @param node Uses the data stored to create a boundary with the
     *             same screen coordinates.
     */

    public Boundary(FieldNode node) {
        this.rightBound = node.getRightX();
        this.topBound = node.getTopY();
        this.bottomBound = node.getBottomY();
        this.leftBound = node.getLeftX();

        if (node instanceof Player) {
            topBound = ((node.getBottomY() - node.getTopY()) / 2) + node.getTopY();
            leftBound = node.getLeftX() * 1.05;
            rightBound = node.getRightX() * .95;
        }
    }

    /**
     * This method calculates whether or not this boundary is contained
     * in the test parameter boundary.
     *
     * @param testBoundary test subject to compare with this current Boundary.
     * @return true, if there is a collision, false if there is no collision.
     */

    public boolean isContained(Boundary testBoundary) {
        return//right side
                (leftBound <= testBoundary.getRightBound() &&
                        //bottom side
                        topBound <= testBoundary.getBottomBound() &&
                        //left
                        rightBound >= testBoundary.getLeftBound()) &&
                        //top side
                        bottomBound >= testBoundary.getTopBound();
    }

    public double getRightBound() {
        return rightBound;
    }

    public double getLeftBound() {
        return leftBound;
    }

    public double getTopBound() {
        return topBound;
    }

    public double getBottomBound() {
        return bottomBound;
    }
}
