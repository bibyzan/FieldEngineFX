package EngineCode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by benra_000 on 5/16/2015.
 */
public class GameDatabase {
    public static GamePreferences globalPreferences;
    public static double globalZoomProperty = 1;
    public static HashMap<String, TileSet> tileSets;
    public static HashMap<String, GenericSpriteSet> spriteSets;

    public static void init() {
        globalPreferences = new GamePreferences();

        InputStream inStream = GameDatabase.class.getClassLoader().getResourceAsStream("Database/SpriteSets/SpriteSets.txt");
        Scanner resourceReader = new Scanner(inStream);

        tileSets = new HashMap<>();
        spriteSets = new HashMap<>();

        while (resourceReader.hasNext()) {
            String setName = resourceReader.nextLine();
            spriteSets.put(setName, new GenericSpriteSet("Database/SpriteSets/" + setName));
        }

        inStream = GameDatabase.class.getClassLoader().getResourceAsStream("Database/TileSets/TileSets.txt");
        resourceReader = new Scanner(inStream);

        while (resourceReader.hasNext()) {
            String setName = resourceReader.nextLine();
            tileSets.put(setName, new TileSet("Database/TileSets/" + setName));
        }
    }
}
