package FieldEngineFX;

/**
 * 9/15/2015
 * Shell class for all moving nodes on the screen.
 * Contained is all the tools needed for the animation
 * of these moving objects, made for use in the World
 * class runner. The abstract isFriendly method is included
 * to force specification as to what kind of FieldActor
 * the Player is interacting with.
 *
 * @author Ben Rasmussen
 */
public abstract class FieldActor extends FieldNode {
    private int animationInvoker, animationCounter;

    protected double speed;
    protected SpriteSet spriteSet;
    protected Action currentAction;
    protected Direction currentDirection;

    /**
     * For use in all subclass constructors.
     * Simple default initialization of all private data
     * included here, in this abstract class.
     *
     * @param spriteSet Graphics for the actor passed in that
     *                may or may not be generic.
     */

    public void initialize(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;

        currentDirection = new Direction(Direction.DOWN);
        currentAction = new Action(Action.IDLE);
        animationInvoker = 40;
        animationCounter = animationInvoker;
        speed = 2;

        super.setImage(spriteSet.rotateImage());
        boundary = new Boundary(this);
    }

    /**
     * For use in the World class. This method moves
     * the FieldActor on the screen assuming the direction
     * and speed haven't changed. Once it moves the Actor,
     * this invokes the animation.
     */

    public void move() {
        this.shift(currentDirection);

        this.animate();
    }

    /**
     * An automatic "undo" of exactly one move
     * method call. For use in testing for collisions.
     */

    public void revert() {
        this.shift(Direction.getOpposite(currentDirection));
    }

    /**
     * Using the animationInvoker to decide whether or not it's time
     * to switch sprites, if it's at 0 then the graphic changes,
     * otherwise the invoker is incremented negatively;
     */

    public void animate() {
        if (animationCounter <= 0) {
            super.setImage(spriteSet.rotateImage());
            animationCounter = animationInvoker;
        } else
            animationCounter--;
    }

    /**
     * Shift method not included in the FieldNode parent class since
     * it does not contain a direction as private data.
     *
     * This takes the direction passed in and moves the Actor
     * in one of the four directions based on the parameter and
     * the speed contained as private data here.
     *
     * @param concreteDirection The direction the Actor is to be moved in,
     *                          this is used as a parameter rather than using
     *                          the current direction so the Actor can be
     *                          shifted backwards or sideways without turning it.
     */

    public void shift(Direction concreteDirection) {
        if (concreteDirection == null)
            concreteDirection = currentDirection;

        if (concreteDirection.equals(Direction.UP))
            this.shiftY(-1 * speed);
        else if (concreteDirection.equals(Direction.RIGHT))
            this.shiftX(speed);
        else if (concreteDirection.equals(Direction.DOWN))
            this.shiftY(speed);
        else if (concreteDirection.equals(Direction.LEFT))
            this.shiftX(-1 * speed);
    }

    /**
     * Setter for currentDirection variable, but included here are
     * several other method calls also adjust the screen graphic according
     * to what the new direction.
     *
     * @param currentDirection New direction to be saved.
     */

    public void setCurrentDirection(Direction currentDirection) {
        if (currentDirection != this.currentDirection) {
            this.currentDirection = currentDirection;
            spriteSet.turn(currentDirection);
            super.setImage(spriteSet.rotateImage());
        }
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    public SpriteSet getSpriteSet() {
        return spriteSet;
    }

    public void setSpriteSet(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getAnimationInvoker() {
        return animationInvoker;
    }

    public void setAnimationInvoker(int animationInvoker) {
        this.animationInvoker = animationInvoker;
    }

    abstract boolean isFriendly();
}
