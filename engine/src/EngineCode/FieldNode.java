package EngineCode;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by benra_000 on 5/18/2015.
 */
public abstract class FieldNode extends ImageView {
    protected Boundary area;
    protected double width, height;

    public ArrayList<Point2D> getContainedPoints() {
        return area.getPoints();
    }


    public Direction getDirectionFacingNode(FieldNode fieldNode) {
        Point2D centerPoint = calcCenterPoint();
        Direction facingDirection;

        if (centerPoint.getX() < fieldNode.getX())
            facingDirection = Direction.RIGHT;
        else if (centerPoint.getX() > fieldNode.getX() + fieldNode.getWidth())
            facingDirection = Direction.LEFT;
        else if (centerPoint.getY() > fieldNode.getY() + fieldNode.getHeight())
            facingDirection = Direction.UP;
        else
            facingDirection = Direction.DOWN;

        return facingDirection;
    }

    public Point2D calcCenterPoint() {
        double x = getX() + (getWidth() / 2);
        double y = getY() + (getHeight() / 2);

        return new Point2D(x,y);
    }

    public boolean isColliding(FieldNode subject) {
        return area.isContained(subject.getContainedPoints());
    }

    public double getRightX() {
        return getX() + getWidth();
    }

    public double getTopY() {
        return getY();
    }

    public double getLeftX() {
        return getX();
    }

    public double getBottomY() {
        return getY() + getHeight();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);

        area = new Boundary(this);
    }

    public void fillImageToDimensions(double width, double height) {
        super.setFitWidth(width);
        super.setFitHeight(height);

        this.height = height;
        this.width = width;
    }

    public void setGraphic(Image image) {
        super.setImage(image);

        width = image.getWidth();
        height = image.getHeight();

        area = new Boundary(this);
    }
}
