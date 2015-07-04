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
    public FieldObject(Image image) {
        super();
        super.setGraphic(image);
    }

    public FieldObject(FieldObject fieldObject) {
        this(fieldObject.getImage());
    }
}
