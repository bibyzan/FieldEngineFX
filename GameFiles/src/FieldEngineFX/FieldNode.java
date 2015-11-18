package FieldEngineFX;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 * 9/9/2015.
 */
public abstract class FieldNode extends ImageView {
    protected double width, height;
    protected Boundary boundary;

    public boolean hasBoundary() {
        return boundary != null;
    }

    public void moveToPoint(Point2D point) {
        changeY(point.getY());
        changeX(point.getX());
    }

    public void changeX(double newX) {
        setX(newX);

        if (hasBoundary())
            boundary = new Boundary(this);
    }

    public void changeY(double newY) {
        setY(newY);

        if (hasBoundary())
            boundary = new Boundary(this);
    }

    public void shiftX(double shift) {
        changeX(getX() + shift);
    }

    public void shiftY(double shift) {
        changeY(getY() + shift);
    }

    public void setFillWidth(double width) {
        this.width = width;
        if (hasBoundary())
            this.boundary = new Boundary(this);
        super.setFitWidth(width);
    }

    public void setFillHeight(double height) {
        this.height = height;
        if (hasBoundary())
            this.boundary = new Boundary(this);
        super.setFitHeight(height);
    }

    public boolean isColliding(FieldNode node) {
        if (!node.hasBoundary())
            return false;

        //prevents null pointer exception
        if (hasBoundary())
            return boundary.isContained(node.getBoundary());

        return false;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public double getRightX() {
        return getX() + width;
    }

    public double getTopY() {
        return getY();
    }

    public double getLeftX() {
        return getX();
    }

    public double getBottomY() {
        return getY() + height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}