package EngineCode;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


/**
 * Displayed as one individual block in game
 */
public class FieldObject extends FieldNode {
    protected ArrayList<Direction> blockedSides;

    public FieldObject(String dirName, String graphicName) {
        super();

        blockedSides = new ArrayList<>();

        try {
            super.setGraphic(new Image(new FileInputStream(new File(dirName + ".area/graphics/" + graphicName + ".png"))));
        } catch (Exception e) {
            System.out.println("couldn't find: " + graphicName);
            e.printStackTrace();
        }
        
    }

    public FieldObject(Image image) {
        super();
        super.setGraphic(image);
    }

    public FieldObject(FieldObject fieldObject) {
        this(fieldObject.getImage());
        this.blockedSides = fieldObject.getBlockedSides();
    }
    

    @Override
    public boolean isColliding(FieldNode subject) {
        if (blockedSides.size() > 0) {
            for (Direction direction : blockedSides)
                if (direction.equals(Direction.ALL)) {
                    for (Point2D subjectPoint : subject.getContainedPoints())
                        if (area.isContained(subjectPoint))
                            return true;
                } else
                    for (Point2D sidePoint : area.getASide(direction))
                        for (Point2D testPoint : subject.getContainedPoints())
                            if (sidePoint.equals(testPoint))
                                return true;
        }

        return false;
    }
    

    public ArrayList<Direction> getBlockedSides() {
        return blockedSides;
    }

    public void setBlockedSides(ArrayList<Direction> blockedSides) {
        this.blockedSides = blockedSides;
    }
}
