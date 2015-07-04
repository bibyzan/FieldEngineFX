package EngineCode;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by benra_000 on 5/16/2015.
 */
public class Field extends Pane {
    protected Grid<FieldObject> grid;

    public Field(String areaDir, String fieldDir, TileSet tileSet) {
        super();
        grid = new Grid<>();
        InputStream inStream = getClass().getClassLoader().getResourceAsStream("Maps/" + areaDir + "/" + fieldDir + ".field");
        Scanner reader = new Scanner(inStream);

        int row = 0;
        while (reader.hasNext()) {
            int col = 0;

            for (String graphic: reader.nextLine().split(" ")) {
                FieldObject temp = tileSet.getCopy(graphic);
                temp.setLocation(col * GameDatabase.globalPreferences.getPixelWidth(),
                        row * GameDatabase.globalPreferences.getPixelHeight());

                grid.put(new Position(row, col), temp);
                col++;
            }

            row++;
        }

        super.getChildren().addAll(grid.values());
    }

    public Position calcPositionAtPoint(Point2D point) {
        for (Position position: grid.keySet())
            if (grid.get(position).contains(point))
                return position;

        return null;
    }

    public ArrayList<Boundary> getBoundariesAroundPoint(Point2D point) {
        ArrayList<Boundary> boundaries = new ArrayList<>();

        for (FieldObject object: grid.getNearbyValues(calcPositionAtPoint(point)))
            boundaries.add(object.getArea());

        return boundaries;
    }

    public Grid<FieldObject> getGrid() {
        return grid;
    }

    public void setGrid(Grid<FieldObject> grid) {
        this.grid = grid;
    }
}
