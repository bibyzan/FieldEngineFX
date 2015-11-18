package FieldEngineFX;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 9/14/15
 * This class is made for use everywhere within the engine.
 * Run the init method at start to fill the HashMaps,
 * then the HashMaps are utilized all over the game to hopefully
 * help program speed with everything pre-loaded.
 *
 * @author Ben Rasmussen
 */
public class Database {
    public static HashMap<String, TileSet> tileSets;
    public static HashMap<String, SpriteSet> spriteSets;

    /**
     * Method is to be called at game launch. Using the SpriteSets.txt
     * and TileSets.txt, this scans those files to aquire the path
     * for every TileSet and SpriteSet to be used in the engine
     * and fill the HashMaps for later use.
     *
     * Any Sets not listed in the txt files will not be read.
     */

    public static void init() {
        tileSets = new HashMap<>();
        spriteSets = new HashMap<>();

        Scanner tileSetReader = new Scanner(Launcher.pullResource("Database/Tile_Sets/TileSets.txt"));

        while (tileSetReader.hasNext()) {
            String line = tileSetReader.nextLine();

            tileSets.put(line, new TileSet(line));
        }
        tileSetReader.close();

        HashMap<String, String> spriteSetInfo = Launcher.retrievePreferences("Database/Sprite_Sets/SpriteSets.txt");

        for (String setName: spriteSetInfo.keySet())
            spriteSets.put(setName, new SpriteSet(setName, spriteSetInfo.get(setName)));
    }
}
