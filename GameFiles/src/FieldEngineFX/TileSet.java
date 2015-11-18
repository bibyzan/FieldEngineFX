package FieldEngineFX;

import java.util.HashMap;

/**
 * Created by Ben on 9/9/2015.
 */
public class TileSet extends HashMap<String, FieldGraphic> {
    public TileSet() {
        super();
    }

    public TileSet(String setName) {
        super(DatabaseParser.loadTileSet(setName));
    }

    public FieldGraphic getCopy(String key) {
        return new FieldGraphic(super.get(key));
    }
}
