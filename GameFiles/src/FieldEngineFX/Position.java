package FieldEngineFX;

import java.util.ArrayList;

/**
 * Created by benra_000 on 5/19/2015.
 */
public class Position {
    private int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public ArrayList<Position> getAdjacentPositions() {
        ArrayList<Position> positions = new ArrayList<>();

        for (int r = row - 1; r <= row + 1; r++)
            for (int c = col - 1; c <= col + 1; c++)
                if (row != r || col != c)
                    positions.add(new Position(r,c));

        return positions;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
