package com.company;

import java.util.List;

/**
 * Created by nivne on 02-APR-17
 */
public abstract class Board {
    public abstract int setCell(int row, int col, int value);
    public abstract Cell getCell(int row, int col);
    public abstract List<Cell> getKnownCells();
    public abstract  int getSizeInCells();
    public abstract void PrintBoard();
}
