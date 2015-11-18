package FieldEngineFX;

import javafx.scene.image.Image;

import java.util.HashMap;

/**
 * Generic sprite set is a set of images meant to be read from a parent directory with the following structure
 *      Inner directory named: sprites
 *          5 more folders with the names: idle, walkLeft, walkRight, walkUp, walkDown
 *          each containing the amount of images indicated by the genericSpriteTemplate.txt
 */
public class SpriteSet {
    protected HashMap<String, SpriteList> spriteMap;
    protected HashMap<String, CommandEvent> customAnimations;

    protected SpriteList currentList;
    protected String setName;

    public SpriteSet(String setName, String template) {
        this.setName = setName;

        spriteMap = new HashMap<>();
        customAnimations = new HashMap<>();
        fillFromTemplate(template);

        currentList = spriteMap.get("idle");
    }

    public void switchList(String listName) {
        currentList = spriteMap.get(listName);
    }

    public void putAnimation(String name, CommandEvent event) {
        customAnimations.put(name, event);
    }

    public void runAnimation(String name) {
        customAnimations.get(name).run();
    }

    public void fillFromTemplate(String templateName) {
        spriteMap.clear();

        HashMap<String, String> template = Launcher.retrievePreferences("Database/Sprite_Sets/Templates/" + templateName + ".txt");

        for (String listDir: template.keySet())
            spriteMap.put(listDir, new SpriteList(setName, listDir, template.get(listDir).split(" ")));

    }

    public void turn(Direction newDirection) {
        if (newDirection.equals(Direction.RIGHT))
            currentList = spriteMap.get("walk_right");
        else if (newDirection.equals(Direction.LEFT))
            currentList = spriteMap.get("walk_left");
        else if (newDirection.equals(Direction.UP))
            currentList = spriteMap.get("walk_up");
        else
            currentList = spriteMap.get("walk_down");
    }

    public boolean isLastImage() {
        return currentList.isLastImage();
    }

    public SpriteList getCurrentList() {
        return currentList;
    }

    public void setCurrentList(SpriteList currentList) {
        this.currentList = currentList;
    }

    public Image rotateImage() {
        return currentList.rotateImage();
    }
}
