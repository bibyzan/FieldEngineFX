package FieldEngineFX;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * 9/4/2015
 * For use in the SpriteSet classes, this class helps organize the different
 * parts of a moving graphic by categorizing the parts of a sprite set.
 * The actual picture graphics are held within these objects as well.
 *
 * ex. Direction left, right, or idle
 *
 * @author Ben Rasmussen
 */
public class SpriteList extends ArrayList<Image> {
    private int index;

    public SpriteList(String setName,String listName,  String[] imageNames) {
        super();
        index = 0;

        for (String imageName: imageNames)
            try {
                super.add(new Image(Launcher.pullResource(
                        "Database/Sprite_Sets/" + setName + "/" + listName + "/" + imageName + ".png")));
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

    public boolean isLastImage() {
        return index + 1 == super.size();
    }
}
