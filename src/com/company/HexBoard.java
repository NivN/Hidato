package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nivne on 02-Apr-17.
 */
public class HexBoard extends Board {

    private Cell[][] BoardTable;
    private final int[][] DirVector = {{-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 0}, {1, -1}};
    private int BoardSize;

    public HexBoard(int BoardSize) {

        this.BoardSize = BoardSize;

        this.BoardTable = new Cell[this.BoardSize][this.BoardSize];

        // create all cells
        // This creates cell in a mirror based pattern to fit hexagon representation
        for (int i = 0; i < ((this.BoardSize / 2) + (this.BoardSize %2)); i++)
        {
            int x = this.BoardSize/2;
            for (int j = 0; j < (x + 1) + i; j++)
            {
                // make sure it's not yet created (if there's an uneven number of lines this is necessary)
                if (this.BoardTable[i][this.BoardSize - 1 - j] == null)
                    this.BoardTable[i][this.BoardSize - 1 - j] = new Cell();

                if (this.BoardTable[this.BoardSize - i - 1][j] == null)
                    this.BoardTable[this.BoardSize - i - 1][j] = new Cell();
            }

        }
        // Fill Relation between cells to form a graph
        for (int i = 0; i < this.BoardSize; i++)
        {
            for (int j = 0; j < this.BoardSize; j++)
            {
                for (int k = 0; k < 6; k++)
                {
                    // Create x\y for next adjacent cell
                    int l = this.DirVector[k][0] + i;
                    int m = this.DirVector[k][1] + j;

                    // See that next cell is not out of the array bounds
                    if (((l) >= 0) && ((l) < this.BoardSize) &&
                            ((m) >= 0) && ((m) < this.BoardSize) &&
                            this.BoardTable[l][m] != null &&
                            this.BoardTable[i][j] != null)
                    {
                        this.BoardTable[i][j].AddAdjacentNode(this.BoardTable[l][m]);
                    }
                }
            }
        }
    }

    // This functions calculates the board size required (X\Y) for a board containing numbersInBoard numbers
    private int BoardSizeFromNumbers(int numbersInBoard) {
        int BoardSize = 1;
        int CurrSize = 1;
        int Increment = 6;

        while (CurrSize < numbersInBoard)
        {
            CurrSize += Increment;
            Increment += 6;
            BoardSize += 2;
        }

        if (CurrSize == numbersInBoard)
        {
            return BoardSize;
        }

        return 0;

    }

    public int setCell(int row, int col, int value)
    {
        if(row < (this.BoardSize/2))
        {
            col = col + ((this.BoardSize/2) - row);
        }

        this.BoardTable[row][col].SetValue(value);

        return value;

    }

    @Override
    public Cell getCell(int row, int col)
    {
        if(row < (this.BoardSize/2))
        {
            col = col + ((this.BoardSize/2) - row);
        }

        return this.BoardTable[col][row];
    }

    @Override
    public List<Cell> getKnownCells()
    {
        List<Cell> Cells = new ArrayList<>();
        for (Cell[] row : this.BoardTable)
        {
            for (Cell c : row)
            {
                if ((c != null) && (c.GetValue() != 0))
                    Cells.add(c);
            }
        }

        return Cells;
    }

    @Override
    public int getSizeInCells() {
        int n = this.BoardSize/2;
        int a = this.BoardSize - (n);

        int cells = (n*((2*a)+(n-1))) + (this.BoardSize * (this.BoardSize%2));

        return cells;
    }

    @Override
    public void PrintBoard()
    {
        int halfboard = this.BoardSize/2;

        for (int i = 0; i < this.BoardSize; i++)
        {
            int spaceLimit = halfboard - i;

            if ( i >= halfboard)
            {
                spaceLimit = i - halfboard;
            }

            for (int k = 0; k < spaceLimit; k++)
            {
                System.out.print("  ");
            }

            for (int j = 0; j < this.BoardSize; j++)
            {
                if (this.BoardTable[i][j] != null)
                {
                    System.out.format("|%02d|", this.BoardTable[i][j].GetValue());
                }
            }

            System.out.println("");
        }
    }
}
