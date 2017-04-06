package com.company;

import java.util.Collections;
import java.util.List;

/**
 * Created by nivne on 06-Apr-17.
 */
public class DfsSolver implements ISolvingAlgorithm
{
    public  int counter = 0;

    @Override
    public boolean solve(Board gameBoard)
    {
        List<Cell> knownCells = gameBoard.getKnownCells();
        Collections.sort(knownCells);

        return DFS(knownCells.get(0), knownCells.get(0).GetValue(),0, knownCells, gameBoard.getSizeInCells() , gameBoard);
    }

    private boolean DFS(Cell CurrCell, int CurrNumber, int NextKnown, List<Cell> knownCells, int BoardSize, Board b)
    {
        counter++;

        if (CurrNumber >= BoardSize)
        {
            return true;
        }

        if (((CurrCell.GetValue() != 0) && (CurrCell.GetValue() != CurrNumber)) ||
                ((knownCells.get(NextKnown).GetValue() == CurrNumber) && (CurrCell.GetValue() == 0)))
        {
            return false;
        }

        if (CurrCell.GetValue() == CurrNumber)
        {
            NextKnown++;
        }

        int OldVal = CurrCell.GetValue();

        CurrCell.SetValue(CurrNumber);

        for(Cell c:CurrCell.GetAdjecentNodes())
        {
            if(DFS(c,CurrNumber+1, NextKnown, knownCells, BoardSize,b))
            {
                System.out.println(counter);
                return true;
            }
        }

        CurrCell.SetValue(OldVal);
        return false;
    }


}
