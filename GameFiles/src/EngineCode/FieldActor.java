package EngineCode;

import java.util.ArrayList;

/**
 * Created by benra_000 on 5/15/2015.
 */
public abstract class FieldActor extends FieldNode {
    private int animationInvoker;

    protected double speed;
    protected GenericSpriteSet spriteSet;
    protected Action currentAction;
    protected Direction currentDirection;

    public void initialize(GenericSpriteSet sprites) {
        spriteSet = sprites;
        currentDirection = new Direction(Direction.DOWN);
        currentAction = new Action(Action.IDLE);
        animationInvoker = 10;
        speed = 2;

        super.setImage(spriteSet.rotateImage());
        area = new Boundary(this);
    }

    public void revert(FieldNode fieldNode) {
        currentAction = Action.IDLE;
        Direction testDirection = fieldNode.getDirectionFacingNode(this);

        if (testDirection.equals(Direction.RIGHT))
            while (getLeftX() <= fieldNode.getRightX() + 1)
                shiftX(1);
        else if (testDirection.equals(Direction.LEFT))
            while (getRightX() >= fieldNode.getLeftX() - 1)
                shiftX(-1);
        else if (testDirection.equals(Direction.DOWN))
            while (getTopY() <= fieldNode.getBottomY() - 1)
                shiftY(-1);
        else if (testDirection.equals(Direction.UP))
            while (getBottomY() >= fieldNode.getTopY() + 1)
                shiftY(1);
    }

    public void move() {
        if (currentAction.equals(Action.MOVING))
            this.shift(currentDirection);

        this.animate();
    }

    public void animate() {
        if (animationInvoker <= 0) {
            super.setImage(spriteSet.rotateImage());
            animationInvoker = 15;
        } else
            animationInvoker--;
    }

    public void shift(Direction concreteDirection) {
        if (concreteDirection.equals(Direction.UP))
            this.shiftY(-1 * speed);
        else if (concreteDirection.equals(Direction.RIGHT))
            this.shiftX(speed);
        else if (concreteDirection.equals(Direction.DOWN))
            this.shiftY(speed);
        else
            this.shiftX(-1 * speed);
    }

    public void shiftX(double shift) {
        super.setX(super.getX() + shift);
        area.shiftX(shift);
    }

    public void shiftY(double shift) {
        super.setY(super.getY() + shift);
        area.shiftY(shift);
    }

    public void turn(Direction direction) {
        spriteSet.turn(direction);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GenericSpriteSet getSpriteSet() {
        return spriteSet;
    }

    public void setSpriteSet(GenericSpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        if (currentDirection != this.currentDirection) {
            this.currentDirection = currentDirection;
            spriteSet.turn(currentDirection);
            super.setImage(spriteSet.rotateImage());
        }
    }

    abstract boolean isFriendly();
}
