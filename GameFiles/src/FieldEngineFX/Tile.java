package FieldEngineFX;

import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * Created by Ben on 9/9/2015.
 */
public class Tile extends Image {
    private boolean collidable;
    private InputStream resourceStream;

    public Tile(Tile tile) {
        super(tile.getResourceStream());

        resourceStream = tile.getResourceStream();
        collidable = tile.isCollidable();
    }

    public Tile(String url, boolean collidable) {
        super(Tile.class.getClassLoader().getResourceAsStream(url));
        resourceStream = Tile.class.getClassLoader().getResourceAsStream(url);

        this.collidable = collidable;
    }

    public InputStream getResourceStream() {
        return resourceStream;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }
}
