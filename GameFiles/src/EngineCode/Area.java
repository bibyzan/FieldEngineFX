package EngineCode;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by benra_000 on 5/17/2015.
 */
public class Area extends Grid<Field> {
    protected TileSet tileSet;

    public Area(String dirName) {
        super();

        InputStream inStream = getClass().getClassLoader().getResourceAsStream("Maps/" + dirName + "/info.txt");
        Scanner reader = new Scanner(inStream);
        tileSet = GameDatabase.tileSets.get(reader.nextLine().split(":")[0]);
        
        String line = reader.nextLine();
        String[] startPos = line.split(":")[1].split(",");

        int row = 0;
        while (reader.hasNext()) {
            int col = 0;

            String[] brokenRow = reader.nextLine().split(" ");
            for (String areaDir: brokenRow) {
                super.put(new Position(row, col), new Field(dirName, areaDir, tileSet));
                col++;
            }

            row++;
        }

        World.currentScreen = super.get(new Position(Integer.parseInt(startPos[0]), Integer.parseInt(startPos[1])));
    }
}
