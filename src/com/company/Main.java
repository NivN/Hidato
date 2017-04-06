package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("enter boards size");

        String boardsize;

        try
        {
            boardsize = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

	    Board b = new HexBoard(11);

        DfsSolver Solver = new DfsSolver();

        System.out.println("enter values to place on the board, use space delimited x y and values");
        String BoardValues = null;

        try
        {
            BoardValues = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] values = BoardValues.split(" ");

        for (int i = 0; i < values.length ; i+=3)
        {
            b.setCell(Integer.valueOf(values[i]),Integer.valueOf(values[i+1]),Integer.valueOf(values[i+2]));
        }

        b.PrintBoard();

        System.out.println("");

        System.out.println(Solver.solve(b));

        System.out.println("");

        b.PrintBoard();
    }
}
