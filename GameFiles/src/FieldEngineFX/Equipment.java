package FieldEngineFX;

import javafx.scene.image.Image;

/**
 * Created by Ben on 9/28/2015.
 */
public abstract class Equipment extends Item {
    public Equipment(String graphicName, boolean sellable, int value, String externalName) {
        super(graphicName, sellable, value, externalName);
    }

    public abstract void animate();
}
