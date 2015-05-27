package EngineCode;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Generic sprite set is a set of images meant to be read from a parent directory with the following structure
 *      Inner directory named: sprites
 *          5 more folders with the names: idle, walkLeft, walkRight, walkUp, walkDown
 *          each containing the amount of images indicated by the genericSpriteTemplate.txt
 */
public class GenericSpriteSet {
    protected SpriteList idle, walkLeft, walkRight, walkUp, walkDown, currentList;

    public GenericSpriteSet(String dirName) {
        InputStream inStream = getClass().getClassLoader().getResourceAsStream("genericSpriteTemplate.txt");
        Scanner streamScanner = new Scanner(inStream);

        String path = dirName + "/sprites";
        idle = new SpriteList(path + "/idle", streamScanner.nextLine().split(" "));
        walkLeft = new SpriteList(path + "/walkLeft", streamScanner.nextLine().split(" "));
        walkRight = new SpriteList(path + "/walkRight", streamScanner.nextLine().split(" "));
        walkUp = new SpriteList(path + "/walkUp", streamScanner.nextLine().split(" "));
        walkDown = new SpriteList(path + "/walkDown", streamScanner.nextLine().split(" "));

        currentList = idle;
    }

    public void turn(Direction newDirection) {
        if (newDirection.equals(Direction.RIGHT))
            currentList = walkRight;
        else if (newDirection.equals(Direction.LEFT))
            currentList = walkLeft;
        else if (newDirection.equals(Direction.UP))
            currentList = walkUp;
        else
            currentList = walkDown;
    }

    public Image rotateImage() {
        return currentList.rotateImage();
    }
}
