package FieldEngineFX;

import java.io.InputStream;
import java.util.Scanner;

/**
 * 9/9/2015
 * Class created for organizational purposes to fill several of the different kinds of objects
 * used in this engine. The methods in this class are used solely to read different aspects of
 * the files used in the resources directory within a jar context to make games made here
 * as portable as possible.
 *
 * @author Ben Rasmussen
 */
public class DatabaseParser {

    /**
     * For use in the Field class constructor. This method uses the .field file as indicated
     * in the constructor. It starts by reading the file as an InputStream from the jar, and
     * determines the the TileSet to be used. After that it scans for the "Layer:" key word
     * to begin rendering the map on the screen within the while loop. It uses the public static
     * screen and grid size variables from the Launcher class to account for how many FieldObjects should be
     * on the screen at one time, but it still loads all excess graphics even if they aren't
     * yet visible. This is in preparation for when the map begins to scroll with the moving player.
     * The loop continues to add FieldObjects to the Field until either the file ends, or it
     * hits another "Layer:" keyword. If that happens then it restarts the process with the new
     * layer by placing all of those objects on top of the previous layer.
     *
     * @param fieldName All that needs to be passed in here is the literal name of the field,
     *                  excluding the file extension. It should load with no problem assuming
     *                  your field files are in the Maps directory.
     */

    public static Field loadField(String fieldName) {
        Scanner reader = new Scanner(Launcher.pullResource("Maps/" + fieldName + ".field"));

        Field newField = new Field();
        TileSet tileSet = new TileSet();

        boolean newLayer = false;

        while (reader.hasNext()) {
            String line = reader.nextLine();

            if (line.split(":")[0].equals("TileSet")) {
                tileSet = Database.tileSets.get(line.split(":")[1]);
            } else if (line.equals("Layer:") || newLayer) {
                int currentX = 0, currentY = 0, row = 0, col = 0;

                final double fillWidth = Launcher.screenWidth / (Launcher.gridWidth);
                final double fillHeight = Launcher.screenHeight / (Launcher.gridHeight);

                Grid<FieldGraphic> fieldGraphicGrid = new Grid<>();

                while (reader.hasNext()) {
                    line = reader.nextLine();
                    currentX = 0;
                    col = 0;

                    if (line.equals("Layer:")) {
                        newLayer = true;
                        break;
                    }

                    for (String tile: line.substring(1).split(" ")) {
                        if (!tile.equals("void")) {
                            FieldGraphic graphic = new FieldGraphic(tileSet.getCopy(tile));
                            graphic.setFillWidth(fillWidth);
                            graphic.setFillHeight(fillHeight);

                            graphic.changeX(currentX);
                            graphic.changeY(currentY);

                            fieldGraphicGrid.put(new Position(row, col), graphic);
                        }
                        col++;
                        currentX += fillWidth;
                    }
                    row++;
                    currentY += fillHeight;
                }

                newField.setScrollable(row > Launcher.gridHeight || row > Launcher.gridWidth);
                newField.getChildren().addAll(fieldGraphicGrid.values());
                newField.setGraphicsGrid(fieldGraphicGrid);
            }
        }

        return newField;
    }

    /**
     * This method is intended for use in the TileSet(String) constructor.
     *
     * Format for TileSets:
     * All TileSets should have their own directory containing all the graphics
     * you plan on using, then a boundaries.txt file containing a line
     * for all graphics you want to use. They need to follow a format
     * that contains the name of the png file, then a colon separating the
     * A or N indicating whether or not there's a collision for that block.
     * ex.
     * graphic:A
     * graphic2:N
     *
     * How this method works:
     * After initializing the newSet object to eventually be returned,
     * the Scanner uses boundaries.txt so it knows the path of all the
     * graphics files to be read and whether or not they will be collisions
     * and passes that possibility in the Tile constructor as a boolean.
     *
     * @param tileSetName The name of the directory in the Database.Tile_Sets
     *                    that contains all the tiles in the set.
     * @return newSet The complete TileSet as indicated by boundaries.txt
     */

    public static TileSet loadTileSet(String tileSetName) {
        TileSet newSet = new TileSet();

        InputStream inStream = Launcher.pullResource("Database/Tile_Sets/" + tileSetName + "/boundaries.txt");
        Scanner reader = new Scanner(inStream);

        while (reader.hasNext()) {
            String[] line = reader.nextLine().split(":");

            boolean collidable = line[1].equals("A");

            newSet.put(line[0], new FieldGraphic(new Tile("Database/Tile_Sets/" + tileSetName + "/" + line[0] + ".png", collidable)));
        }

        return newSet;
    }
}
