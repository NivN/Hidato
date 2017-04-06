package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nivne on 02-Apr-17.
 */
public class Cell implements Comparable
{
    private List<Cell> adjecentNodes;
    private int value;

    public Cell()
    {
        this.adjecentNodes = new ArrayList<>();
        this.value = 0;
    }

    public Cell AddAdjacentNode(Cell node)
    {
        this.adjecentNodes.add(node);

        return(this);
    }


    public int SetValue(int newValue)
    {
        return (this.value = newValue);
    }

    public int GetValue()
    {
        return this.value;
    }

    public List<Cell> GetAdjecentNodes()
    {
        return this.adjecentNodes;
    }

    @Override
    public int compareTo(Object o)
    {
        return this.value - ((Cell)o).GetValue();
    }
}