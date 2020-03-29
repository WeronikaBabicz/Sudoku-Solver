package puzzleInfo;

import java.util.ArrayList;

public class Board {
    public static int SUDOKU_SIZE = 9;
    private ArrayList<ArrayList<Integer>> board;

    public Board(ArrayList<ArrayList<Integer>> board) {
        this.board = board;
    }
}
