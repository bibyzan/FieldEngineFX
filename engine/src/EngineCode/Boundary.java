package EngineCode;

import javafx.geometry.Point2D;

import java.util.ArrayList;

/**
 * Created by benra_000 on 5/17/2015.
 */
public class Boundary {
    private double rightBound, leftBound, topBound, bottomBound;

    public Boundary(double rightBound, double leftBound, double topBound, double bottomBound) {
        this.rightBound = rightBound;
        this.leftBound = leftBound;
        this.topBound = topBound;
        this.bottomBound = bottomBound;
    }

    public Boundary(FieldNode fieldNode) {
        leftBound = fieldNode.getX();
        rightBound = fieldNode.getX() + fieldNode.getWidth();

        if (fieldNode instanceof  FieldActor)
            topBound = fieldNode.getY() + (fieldNode.getHeight() / 2);
        else
            topBound = fieldNode.getY();

        bottomBound = fieldNode.getY() + fieldNode.getHeight();
    }

    public ArrayList<Point2D> getASide(Direction direction) {
        ArrayList<Point2D> side = new ArrayList<>();

        if (direction.equals(Direction.LEFT))
            side.addAll(getARange(topBound, bottomBound, leftBound, -1));
        else if (direction.equals(Direction.RIGHT))
            side.addAll(getARange(topBound, bottomBound, rightBound, -1));
        else if (direction.equals(Direction.UP))
            side.addAll(getARange(leftBound, rightBound, -1, topBound));
        else if (direction.equals(Direction.DOWN))
            side.addAll(getARange(leftBound, rightBound, -1, bottomBound));

        return side;
    }

    public void shiftX(double shift) {
        leftBound += shift;
        rightBound += shift;
    }

    public void shiftY(double shift) {
        topBound += shift;
        bottomBound += shift;
    }

    private ArrayList<Point2D> getARange(double start, double finish, double x, double y) {
        ArrayList<Point2D> points = new ArrayList<>();

        for (double i = (int)start; i <= finish; i++)
            if (x == -1)
                points.add(new Point2D(i, y));
            else if (y == -1)
                points.add(new Point2D(x, i));

        return points;
    }

    public boolean isContained(Point2D point) {
        return point.getX() >= leftBound && point.getX() <= rightBound &&
                point.getY() >= topBound && point.getY() <= bottomBound;
    }

    public boolean isContained(Boundary subject) {
        return isContained(subject.getPoints());
    }

    public boolean isContained(ArrayList<Point2D> points) {
        for (Point2D localPoints: getPoints())
            for (Point2D subjectPoints: points)
                if (localPoints.equals(subjectPoints))
                    return true;

        return false;
    }

    public ArrayList<Point2D> getPoints() {
        ArrayList<Point2D> points = new ArrayList<>();

        for (double x = (int)leftBound; x <= rightBound; x++)
            for (double y = (int)topBound; y <= bottomBound; y++)
                points.add(new Point2D(x,y));

        return points;
    }

    public void changeInX(double newX, FieldNode fieldNode) {
        leftBound = newX;
        rightBound = newX + fieldNode.getWidth();
    }

    public void changeInY(double newY, FieldNode fieldNode) {
        topBound = newY;
        bottomBound = newY + fieldNode.getHeight();
    }

    public double getRightBound() {
        return rightBound;
    }

    public void setRightBound(double rightBound) {
        this.rightBound = rightBound;
    }

    public double getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(double leftBound) {
        this.leftBound = leftBound;
    }

    public double getTopBound() {
        return topBound;
    }

    public void setTopBound(double topBound) {
        this.topBound = topBound;
    }

    public double getBottomBound() {
        return bottomBound;
    }

    public void setBottomBound(double bottomBound) {
        this.bottomBound = bottomBound;
    }
}
