package sudokuInfo;

import java.util.ArrayList;

public class Board {
    public static final int SUDOKU_SIZE = 9;
    private ArrayList<ArrayList<Cell>> board;

    public Board(ArrayList<ArrayList<Cell>> board) {
        this.board = board;
    }

    public Board(Board b) {
        this.board = new ArrayList<ArrayList<Cell>>(b.getBoard());
    }

    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }


    public Cell getCellFromBoard(int row, int column){
        return board.get(row).get(column);
    }


    private boolean isFilled(){
        for (ArrayList<Cell> row : board){
            for (Cell cell : row){
                if (cell.getValue() == Cell.EMPTY_CELL_VALUE)
                    return false;
            }
        }
        return true;
    }

    public boolean isSolved(){
        return isFilled();
    }



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ArrayList<Cell> row: board){
            for (Cell cell: row)
                s.append(cell).append(" ");

            s.append("\n");
        }
        return String.valueOf(s);
    }
}
