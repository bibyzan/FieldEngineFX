package EngineCode;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benra_000 on 5/15/2015.
 */
public class SpriteList extends ArrayList<Image> {
    private int index;

    public SpriteList(String directory, String[] imageNames) {
        super();
        index = 0;

        for (String fileName: imageNames)
            try {
                super.add(new Image(getClass().getClassLoader().getResourceAsStream(directory + "/" + fileName + ".png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public Image rotateImage() {
        Image changingImage = super.get(index);

        index++;

        if (index == super.size())
            index = 0;

        return changingImage;
    }
}
