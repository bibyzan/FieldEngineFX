package FieldEngineFX;

/**
 * Created by Ben on 9/9/2015.
 */
public class FieldGraphic extends FieldNode {
    public FieldGraphic(Tile tile) {
        setImage(tile);

        if (tile.isCollidable())
            boundary = new Boundary(this);
        else
            boundary = null;
    }

    public FieldGraphic(FieldGraphic copy) {
        setImage(copy.getImage());

        if (copy.hasBoundary())
            boundary = copy.getBoundary();
        else
            boundary = null;
    }
}
