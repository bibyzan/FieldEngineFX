package EngineCode;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by benra_000 on 5/19/2015.
 */
public class TileSet extends HashMap<String, FieldObject> {
    public TileSet(String dirName) {
        super();
        InputStream inStream = getClass().getClassLoader().getResourceAsStream(dirName + "/Boundaries.txt");
        Scanner streamReader = new Scanner(inStream);

        while (streamReader.hasNext()) {
            String[] splitLine = streamReader.nextLine().split(":");
            String imagePath = splitLine[0];
            String boundaries = splitLine[1];

            Image image = new Image(getClass().getClassLoader().getResourceAsStream(dirName + "/" + imagePath + ".png"));
            FieldObject temp = new FieldObject(image);
            temp.setBlockedSides(Direction.getDirectionsFromSides(boundaries));

            super.put(imagePath, temp);
        }
    }

    public FieldObject getCopy(Object key) {
        return new FieldObject(super.get(key));
    }
}
